## 6. 표현 계층 컨트롤러 (`BoardApiController.java`)

### [코드]
```java
package glitched.controller;

import glitched.dto.BoardResponseDto;
import glitched.dto.BoardSaveRequestDto;
import glitched.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/boards")
@RequiredArgsConstructor
public class BoardApiController {

    private final BoardService boardService;

    @PostMapping
    public ResponseEntity<Long> save(@RequestBody BoardSaveRequestDto requestDto) {
        return ResponseEntity.ok(boardService.save(requestDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> update(@PathVariable Long id, @RequestBody BoardSaveRequestDto requestDto) {
        return ResponseEntity.ok(boardService.update(id, requestDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BoardResponseDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(boardService.findById(id));
    }
}
```

### 1. 왜 이걸 썼는가? (사용 이유)
* 외부 클라이언트의 HTTP 요청(GET, POST, PUT 등)을 받아 매핑하고, 비즈니스 로직 처리 결과를 규격화된 HTTP 응답 형태로 반환하기 위해 사용합니다.

### 2. 어떻게 썼는가? (구현 방법)
* `@RestController`로 JSON 응답 변환을 지정하고, `@RequestMapping`, `@PathVariable`, `@RequestBody`를 통해 요청 데이터와 URI 매핑을 처리했습니다.

### 3. 왜 이렇게 설계했는가? (설계 의도)
* **단일 책임 원칙(SRP)**: 컨트롤러 내부에는 비즈니스 로직이나 DB 접근 코드를 넣지 않고, 요청 검증 및 응답 전달 역할만 수행하도록 설계했습니다.
* **RESTful API 표준 준수**: 자원은 URI(`/api/v1/boards`)로 명시하고, 행위는 HTTP Method(POST, PUT, GET)로 분리하며, `ResponseEntity`를 사용해 표준 HTTP 상태 코드를 함께 리턴합니다.