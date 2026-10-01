## 7. 전역 예외 처리기 (`GlobalExceptionHandler.java`)

### [코드]
```java
package glitched.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> handleIllegalArgumentException(IllegalArgumentException e) {
        Map<String, String> response = new HashMap<>();
        response.put("error", "BAD_REQUEST");
        response.put("message", e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
```

### 1. 왜 이걸 썼는가? (사용 이유)
* 애플리케이션 전역에서 발생하는 예외(Exception)를 일괄적으로 포착하여 클라이언트에 정돈된 에러 응답을 전달하기 위해 사용합니다.

### 2. 어떻게 썼는가? (구현 방법)
* `@RestControllerAdvice` 클래스를 선언하고, `@ExceptionHandler(예외클래스.class)` 메서드를 작성하여 해당 예외 발생 시 포착해 처리합니다.

### 3. 왜 이렇게 설계했는가? (설계 의도)
* **중앙 집중식 예외 관리**: 컨트롤러나 서비스마다 `try-catch` 구문을 중복 작성하지 않도록 예외 처리 로직을 한곳으로 집약했습니다.
* **시스템 보안 및 일관성**: 서버 내부 스택 트레이스(500 Internal Server Error)가 외부에 그대로 노출되는 것을 방지하고, 명확한 에러 메시지와 적절한 HTTP 상태 코드(400 Bad Request 등)를 커스텀하여 일관된 JSON 형식으로 반환합니다.