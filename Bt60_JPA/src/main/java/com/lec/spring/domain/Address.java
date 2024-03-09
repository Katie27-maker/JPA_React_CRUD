package com.lec.spring.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable   // Embed 할수 있는 클래스 임을 선언 --> Entity 안에 선언 가능해진다.
public class Address {
    private String city;
    private String district;

    @Column(name = "address detail")    // 비록 Entity 는 아니지만 @Column 지정 가능
    private String detail;
    private String zipCode;
}