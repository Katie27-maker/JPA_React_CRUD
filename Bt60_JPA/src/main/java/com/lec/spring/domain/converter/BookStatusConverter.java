package com.lec.spring.domain.converter;

import com.lec.spring.domain.BookStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * AttributeConverter<X, Y> 을 implement 하여 Converter 생성
 *     X: 엔티티의  attribute 타입
 *     Y: DB column 타입
 */

@Converter(autoApply = true)
public class BookStatusConverter implements AttributeConverter<BookStatus, Integer> {

    // BookStatus -> DB 컬럼 으로 변환
    @Override
    public Integer convertToDatabaseColumn(BookStatus attribute) {
        return attribute.getCode();
    }

    // DB컬럼값 -> BookStatue 로 변환
    @Override
    public BookStatus convertToEntityAttribute(Integer dbData) {
        // Book 에서 BookStatus 속성은 not null 을 지정해주지도 않은 상태다
        // 따라서, 여기서 Integer 값이 null 이 넘어올수도 있다.
        // Converter 는 DB에 대해 거의 직접적으로 동작하기 때문에
        // 예외등이 발생하면 추적하기 곤란한다.  가급적 예외가 발생안하도록 해야 한다

        return dbData != null ? new BookStatus(dbData) : null;
    }
}