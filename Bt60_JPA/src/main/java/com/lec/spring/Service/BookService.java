package com.lec.spring.Service;

import com.lec.spring.domain.Book;
import com.lec.spring.repository.BookRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    // @Converter에서 'DB'로 변환하는 코드가 없는 경우

    @Autowired
    BookRepository bookRepository;

    @Transactional
    public List<Book> getAll(){
        List<Book> books = bookRepository.findAll();
        books.forEach(System.out::println);
        return books;
    }
}

