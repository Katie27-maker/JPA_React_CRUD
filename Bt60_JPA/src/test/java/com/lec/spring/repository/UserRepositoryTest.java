package com.lec.spring.repository;

import com.lec.spring.domain.Book;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    void beforeEach(TestInfo testInfo){
        System.out.println("─".repeat(40));


        String displayName = testInfo.getDisplayName();
        System.out.println("[ " + displayName + " ] 호출");
    }

    // @AfterEach : 매 테스트 메소드가 종료된 직후에 실행
    @AfterEach
    void afterEach(){
        System.out.println("─".repeat(40) + "\n");
    }

    //------------------------------------------------------------
    // 커스텀 쿼리
    @Test
    void queryTest1(){
        var result = bookRepository.findByCategoryIsNullAndNameEqualsAndCreatedAtGreaterThanEqualAndCreatedAtLessThanEqual(
                "JPA 완전정복",
                LocalDateTime.now().minusDays(1L),
                LocalDateTime.now().plusDays(1L)
        );
        System.out.println(result);

        result = bookRepository.findByNameRecently("허지우 완전전복", LocalDateTime.now().minusDays(1L), LocalDateTime.now().plusDays(1L));
        System.out.println(result);


        result = bookRepository.findByNameRecently2("스프링 완전정복", LocalDateTime.now().minusDays(1L), LocalDateTime.now().plusDays(1L));
        System.out.println(result);


    }

    @Test
    void queryTest4(){
        bookRepository.findBookNameAndCategory1().forEach(tuple -> {
            System.out.println(tuple.get(0) + " : " + tuple.get(1));
        });
    }

    @Test
    void queryTest5(){
        bookRepository.findBookNamdAndCategory2().forEach(b -> {
            System.out.println(b.getName() + " : " + b.getCategory());
        });
    }

    @Test
    void queryTest6(){
        bookRepository.find허지우().forEach(b -> {
            System.out.println(b.getName() + " : " + b.getCategory());
        });
    }

    @Test
    void queryTest7(){
        bookRepository.find불주먹(PageRequest.of(1, 2)).forEach(b -> {
            System.out.println(b.getName() + " : " + b.getCategory());
        });
    }

    //------------------------------------------------------------------------
    // Native Query Test

    @Test
    void nativeQueryTest1(){
        System.out.println("-".repeat(20));
        bookRepository.findAll().forEach(System.out::println);
        System.out.println("-".repeat(20));
        bookRepository.findAllCustom1().forEach(System.out::println);

    }

    // update 성능이슈 관련, 기존의 방식..
    @Test
    void nativeQueryTest2(){
        List<Book> books = bookRepository.findAll();

        for(Book book : books){
            book.setCategory("IT 전문서");
        }

        bookRepository.saveAll(books); // update 문은 몇번 발생?

        //  ↑ 데이터 양이 많아지면 속도의 저하 발생.
        System.out.println(bookRepository.findAll());
    }

    @Test
    void nattiveQueryTest3(){
        System.out.println("affected rows: " + bookRepository.updateCategories());
        System.out.println(bookRepository.findAllCustom1());
    }

    @Test
    void nativeQueryTest4(){
        System.out.println(bookRepository.showTables());
    }

    //------------------------------------------------
    // Converter
    @Test
    void converterTest1(){
        bookRepository.findAll().forEach(System.out::println);
    }

    @Test
    void converterTest2(){

    }

    @Test
    void converterTest3(){
        bookRepository.findAll().forEach(System.out::println);
        bookRepository.findAll().forEach(System.out::println);
    }

    @Test
    void embededTest2(){

    }

}