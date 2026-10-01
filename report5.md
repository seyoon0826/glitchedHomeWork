## 5. 비즈니스 로직 서비스 계층 (`BoardService.java`)

### [코드]
```java
package glitched.service;

import glitched.domain.Board;
import glitched.dto.BoardResponseDto;
import glitched.dto.BoardSaveRequestDto;
import glitched.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public Long save(BoardSaveRequestDto requestDto) {
        return boardRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, BoardSaveRequestDto requestDto) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        board.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    @Transactional(readOnly = true)
    public BoardResponseDto findById(Long id) {
        Board entity = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        return new BoardResponseDto(entity);
    }
}
```

### 1. 왜 이걸 썼는가? (사용 이유)
* 데이터 검증, 예외 처리, 트랜잭션 단위 보장 등 핵심 비즈니스 로직을 수행하기 위해 사용합니다.

### 2. 어떻게 썼는가? (구현 방법)
* `@Service`로 클래스를 스프링 Bean에 등록하고, 메서드 단위에 `@Transactional`을 부여했습니다. `@RequiredArgsConstructor`를 통해 `BoardRepository`를 생성자 주입 방식으로 바인딩했습니다.

### 3. 왜 이렇게 설계했는가? (설계 의도)
* **더티 체킹(Dirty Checking) 활용**: `update` 메서드 내에서 `repository.save()`를 호출하지 않습니다. 영속성 컨텍스트가 유지되는 상태에서 엔티티 값만 변경하면, 트랜잭션 종료 시점에 변경 사항을 감지하여 자동 `UPDATE` 쿼리가 실행되도록 설계했습니다.
* **조회 성능 최적화**: 단순 조회 메서드에는 `@Transactional(readOnly = true)`를 적용해 JPA의 변경 감지 스냅샷 관리 오버헤드를 줄였습니다.