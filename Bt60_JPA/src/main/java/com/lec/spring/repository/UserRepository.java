package com.lec.spring.repository;

import com.lec.spring.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;

// Repository 생성
// JpaRepository<Entity타입, Id타입> 상속 ← 바로 이게 Spring Data JPA 가 지원해주는 영역!
//   상속받은 것만으로도 많은 JPA 메소드를 편리하게 사용 가능하게 된다.
//   상속받은 것만으로도 이미 Spring Context 에 생성된다.
public interface UserRepository extends JpaRepository<User, Long> {

    // QueryMethod 의 다양한 리턴타입들
    //User findByName(String name);  // 단일 객체를 리턴하는 경우만 가능, 여러개 SELECT 되면 예외 발생
    //List<User> findByName(String name);  // 여러 객체를 SELECT 하게 되는 경우.
    Set<User> findByName(String name);

    // 002
    User findByEmail(String email);
    User getByEmail(String email);
    User readByEmail(String email);
    User queryByEmail(String email);
    User searchByEmail(String email);
    User streamByEmail(String email);

    User findUserByEmail(String email);  // 가독성 측면에서 추천.  복수개 리턴하는경우 findUsersBy...()

    // 3.  find 아무말 By....
    User findSomethingByEmail(String email);

    // 4. 잘못된 네이밍
    //User findByByName(String name);  // 'ByName' 이라는 Property 를 찾게 된다! <- 스프링 가동시 에러 발생!

    // 5. First, Top
    // First 와 Top 은 둘다 동일 (가독성 차원에서 제공되는 것임)

    List<User> findFirst1ByName(String name);
    List<User> findFirst2ByName(String name);
    List<User> findTop1ByName(String name);
    List<User> findTop2ByName(String name);

    // 6. 혹시 Last 는 없나요?
    // Last 는 없는 키워드.  find 와 by 사이의 키워드도 아닌 것들은 무시된다.
    List<User> findLast1ByName(String name);  // 걍 findByName() 과 동일한 동작.

    // 7. And, Or
    List<User> findByEmailAndName(String email, String name);
    List<User> findByEmailOrName(String email, String name);

    // 8. After, Before
    List<User> findByCreatedAtAfter(LocalDateTime datetime);
    List<User> findByIdAfter(Long Id);
    List<User> findByNameBefore(String name);

    //9. GreaterThan, GreaterThanEqual, LessThan, LessThanEqual
    List<User> findByCreatedAtGreaterThan(LocalDateTime datetime);
    List<User> findByNameGreaterThanEqual(String name);

    // 10. Between
    List<User> findByCreatedAtBetween(LocalDateTime datetime1, LocalDateTime datetime2);
    List<User> findByIdBetween(Long id1, Long id2);
    List<User> findByIdGreaterThanEqualAndIdLessThanEqual(Long id1, Long id2);

    // 11. Null, Empty
    List<User> findByIdIsNotNull();

//    List<User> findByNameIsNotEmpty();  // Empty 는 Collection 에 대해 사용해야 한다

//    List<User> findByAddressIsNotEmpty();

    // 12. In, NotIn
    List<User> findByNameIn(List<String> names);

    // 13. StartingWith, EndingWith, Contains
    // 문자열에 대한 검색쿼리, LIKE 사용
    List<User> findByNameStartingWith(String name);
    List<User> findByNameEndingWith(String name);
    List<User> findByEmailContains(String email);

    // 14. Like
    List<User> findByEmailLike(String email);

    // 15.Is, Equals
    // 특별한 역할 없다. 생략가능.  가독성 차원에서 제공되는 키워드
    Set<User> findByNameIs(String name);   // findByName 과 동일
    // 아래 4개는 동일하게 동작하는 메소드다.
    // Set<User> findByName(String name);
    // Set<User> findUserByName(String name);
    // Set<User> findUserByNameIs(String name);
    // Set<User> findUserByNameEquals(String name);

    // 16. OrderBy
    List<User> findTopByNameOrderByIdDesc(String name);
    List<User> findFirstByNameOrderByIdDesc(String name);

    // 17. 정렬기준 추가
    List<User> findFirstByNameOrderByIdDescEmailDesc(String name);

    // 18 Sort 객체를 매개변수로 사용한 정렬
    List<User> findFirstByName(String name, Sort sort);

    // 19. Paging
    Page<User> findByName(String name, Pageable pageable);

    // 20. Enum 처리
    @Query(value = "SELECT * FROM T_USER LIMIT 1", nativeQuery = true)
    Map<String, Object> findRowRecord();

}











