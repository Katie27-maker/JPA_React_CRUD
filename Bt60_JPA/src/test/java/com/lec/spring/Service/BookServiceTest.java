package com.lec.spring.Service;

import com.lec.spring.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookServiceTest {

    @Autowired
    BookRepository bookRepository;
    @Autowired
    BookService bookService;

    @Test
    void converterErrorTest1(){
        bookService.getAll();
        bookRepository.findAll().forEach(System.out::println);
    }

}