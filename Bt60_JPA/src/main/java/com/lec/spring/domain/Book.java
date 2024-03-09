package com.lec.spring.domain;

import com.lec.spring.domain.converter.BookStatusConverter;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
public class Book extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String category;

    //-------------------
    // Converter 예제
//    private int status;   // 판매 상태 (정수값으로 표현)
//
//    public boolean isDisplayed(){
//        return status == 200;
//    }

    private  BookStatus status;  // Converter 가 필요하다!
}