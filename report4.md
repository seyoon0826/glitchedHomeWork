## 4. 요청/응답 DTO 계층 (`BoardSaveRequestDto.java` / `BoardResponseDto.java`)

### [코드]
```java
// BoardSaveRequestDto.java (요청용)
package glitched.dto;

import glitched.domain.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BoardSaveRequestDto {
    private String title;
    private String content;

    public Board toEntity() {
        return Board.builder()
                .title(title)
                .content(content)
                .build();
    }
}

// BoardResponseDto.java (응답용)
package glitched.dto;

import glitched.domain.Board;
import lombok.Getter;

@Getter
public class BoardResponseDto {
    private final Long id;
    private final String title;
    private final String content;

    public BoardResponseDto(Board entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
    }
}
```

### 1. 왜 이걸 썼는가? (사용 이유)
* 데이터베이스 엔티티(Entity) 객체를 외부에 직접 노출하지 않고, 클라이언트와 서버 간 HTTP 통신 스펙에 최적화된 전송 객체를 따로 두기 위해 사용합니다.

### 2. 어떻게 썼는가? (구현 방법)
* 요청(Request) DTO에는 엔티티 변환 메서드(`toEntity`)를 구현하고, 응답(Response) DTO에는 엔티티를 파라미터로 받아 필드를 매핑하는 생성자를 작성했습니다.

### 3. 왜 이렇게 설계했는가? (설계 의도)
* **엔티티와 API 스펙의 격리(Decoupling)**: DB 스키마(Entity) 구조가 변경되더라도 외부 API 규격(DTO)이 변경되지 않도록 보호합니다.
* **보안 및 트래픽 최적화**: 엔티티 전체를 반환할 때 발생하는 민감 정보 유출 및 불필요한 연관관계 데이터 전송을 막고, 필요한 데이터만 선택적으로 반환합니다.