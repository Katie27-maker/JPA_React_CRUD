package com.lec.spring.repository;

import com.lec.spring.domain.Book;
import jakarta.persistence.Tuple;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface BookRepository extends JpaRepository<Book, Long> {

    // 다음과 같은 쿼리 메소드를 만들어 보자
    //   Category 는 null이면서
    //    Name 은 name
    //    CreatedAt 은 createdAt 보다 같거나 크고
    //    UpdatedAt 은 updatedAt 보다 같거나 크고


    List<Book> findByCategoryIsNullAndNameEqualsAndCreatedAtGreaterThanEqualAndCreatedAtLessThanEqual(String name, LocalDateTime datetime1, LocalDateTime datetime2);

    // 커스텀 쿼리를 사용하는 이유1
    //  너무 긴 메소드... 가독성 떨어짐.

    // @Query 를 사용한 커스텀 쿼리

    // JPQL Positional parameter 사용, "?" 접두어로 시작
    @Query(value = """
        select b from Book b
        where b.name = ?1 and b.createdAt >= ?2 and b.createdAt <= ?3 and b.category is null
        """)
    List<Book> findByNameRecently(String name, LocalDateTime datetime1, LocalDateTime datetime2);

    // JPQL named parameter 사용, ":" 접두어로 시작
    @Query(value = """
        select b from Book b
        where b.name = :name and b.createdAt >= :datetime1 and b.createdAt <= :datetime2 and b.category is null
        """)
    List<Book> findByNameRecently2(String name, LocalDateTime datetime1, LocalDateTime datetime2);

    // @Query 를 사용하는 이유2
    // 엔티티에 연결되지 않은 쿼리가 가능
//    @Query("select b.name as name, b.category as category from Book b")
    @Query("select b.category as category, b.name as name from Book b")
    List<Tuple> findBookNameAndCategory1();

    // interface 를 활용.  query 결과가 매핑된 객체를 받을수 있다.
    @Query("select b.name as name, b.category as category from Book b")
    List<BookNameAndCategory1> findBookNamdAndCategory2();

    // entity 가 아닌 특정 객체에 query 결과 매핑할때 사용
    @Query("""
        select
            new com.lec.spring.repository.BookNameAndCategory2(b.name, b.category)
        from Book b
        """)
    List<BookNameAndCategory2> find허지우();

    // @Query + Pageable
    @Query("""
        select
            new com.lec.spring.repository.BookNameAndCategory2(b.name, b.category)
        from Book b
        """)
    Page<BookNameAndCategory2> find불주먹(Pageable pageeable);

    //------------------------------------------------------------------
    // Native Query
    //  남발하진 말고, 다음의 경우에 사용 고려를 해보자
    //    1. JPA 의 성능이슈를 해결하기 위해
    //    2. JPA 에서 제공하지 않는 기능을 사용하기 위해

    @Query(value = "select * from book", nativeQuery = true)
    List<Book> findAllCustom1();

    // native query 로 update 하기
    @Transactional   // update / delete 수행하는 @Query 수행시 꼭 명시
    @Modifying    // UPDATE, INSERT , DELETE  수행하는 @Query 임을 명시
    @Query(value = "update book set category = 'IT 전문서'", nativeQuery = true)
    int updateCategories();
    // DML 의 경우 리턴타입이 void, int, long 일수 있다.
    // int 나 long 리턴하게 되면 affecgted row 를 리턴받을수 있다.

    // native query 로 JPA 에 없는 쿼리 수행
    @Query(value = "show tables", nativeQuery = true)
    List<String> showTables();

    // ※ native query 는 어쩔수 없는 경우에만 사용하자.

    //-----------------------------------------------------------------------
    @Query(value = "select * from book order by id desc limit 1", nativeQuery = true)
    Map<String, Object> findRowRecord();

}

interface BookNameAndCategory1 {
    String getName();
    String getCategory();
}

@Data
@AllArgsConstructor
@NoArgsConstructor
// 이건 엔티티가 아니다!
class BookNameAndCategory2 {
    private String name;
    private String category;
}






