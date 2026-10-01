## 2. 도메인 엔티티 (`Board.java`)

### [코드]
```java
package glitched.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "board")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Builder
    public Board(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
```

### 1. 왜 이걸 썼는가? (사용 이유)
* RDB의 `board` 테이블과 자바 클래스를 1:1로 매핑하여, SQL문 작성 없이 객체 지향적으로 데이터 구조를 다루기 위해 사용합니다.

### 2. 어떻게 썼는가? (구현 방법)
* `@Entity` 및 `@Table`로 테이블 매핑을 선언하고, `@Id`와 `@GeneratedValue(strategy = GenerationType.IDENTITY)`로 PK 자동 증가(`AUTO_INCREMENT`) 옵션을 정의했습니다.
* `@Builder` 패턴 생성자와 수정용 비즈니스 메서드(`update`)를 내부에 작성했습니다.

### 3. 왜 이렇게 설계했는가? (설계 의도)
* **Setter 오용 방지 (불변성 유지)**: `@Setter` 어노테이션을 배제하여 외부에서 엔티티 값이 무분별하게 변경되는 것을 막고, 목적이 명확한 `update()` 메서드나 빌더를 통해서만 값이 변경되도록 데이터 무결성을 보장했습니다.
* **기본 생성자 접근 제한**: `@NoArgsConstructor(access = AccessLevel.PROTECTED)`를 설정하여 외부에서 인자 없는 불완전한 엔티티 객체를 무단 생성하는 것을 방지했습니다.