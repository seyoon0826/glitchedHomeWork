## 3. 리포지토리 인터페이스 (`BoardRepository.java`)

### [코드]
```java
package glitched.repository;

import glitched.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
```

### 1. 왜 이걸 썼는가? (사용 이유)
* DB 영속성 계층(Persistence Layer)에 접근하여 CRUD(생성, 조회, 수정, 삭제) 작업을 처리하기 위해 사용합니다.

### 2. 어떻게 썼는가? (구현 방법)
* `JpaRepository<엔티티, PK타입>` 인터페이스를 상속받는 것만으로 별도의 구현 클래스 작성 없이 모든 데이터 접근 기능을 확보했습니다.

### 3. 왜 이렇게 설계했는가? (설계 의도)
* **반복적인 DAO/SQL 구현 제거**: Spring Data JPA의 인터페이스 기반 추상화를 활용하여 `save()`, `findById()`, `findAll()`, `delete()` 등의 반복 구현을 완전히 생략했습니다.
* **계층 분리**: 데이터베이스에 직접 접근하는 역할을 전담시켜 서비스 계층이 DB 기술 종속성 없이 비즈니스 로직에만 집중할 수 있게 설계했습니다.