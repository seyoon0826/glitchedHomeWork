## 1. 메인 애플리케이션 클래스 (`Glitchedstudy1Application.java`)

### [코드]
```java
package glitched;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Glitchedstudy1Application {

    public static void main(String[] args) {
        SpringApplication.run(Glitchedstudy1Application.class, args);
    }

}
```

### 1. 왜 이걸 썼는가? (사용 이유)
* Spring Boot 애플리케이션의 **최초 진입점(Entry Point)**으로, 자바 표준 `main` 메서드를 통해 내장 서블릿 컨테이너(Tomcat)와 Spring IoC 컨테이너를 가동하기 위해 사용합니다.

### 2. 어떻게 썼는가? (구현 방법)
* `@SpringBootApplication` 어노테이션을 루트 패키지 클래스에 부여하고, `SpringApplication.run()` 메서드에 실행 클래스와 파라미터를 넘겨 구동합니다.

### 3. 왜 이렇게 설계했는가? (설계 의도)
* **컴포넌트 스캔 기준점 제공**: `@SpringBootApplication` 내부의 `@ComponentScan`이 실행 클래스가 위치한 `glitched` 패키지 하위를 자동으로 탐색하여 어노테이션이 붙은 객체들을 Bean으로 등록합니다.
* **자동 설정(Auto Configuration) 활성화**: 개발자가 일일이 자바 코드로 Bean을 등록하지 않아도 프레임워크가 필요 객체들을 자율적으로 초기화하도록 최소한의 코드로 추상화했습니다.

---