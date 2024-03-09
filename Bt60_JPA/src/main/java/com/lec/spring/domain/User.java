package com.lec.spring.domain;

import com.lec.spring.domain.listener.UserEntityListener;
import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity(name = "T_USER")
//@EntityListeners(value = UserEntityListener.class)
public class User extends BaseEntity {

    @Id  // PK
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // AI
    private Long id;

    @NonNull // lombok.NonNull
    private String name;
    @NonNull
    @Column(unique = true)
    private String email;

    // 주소정보 추가, Embeddable 사용안한 경우
//    private String city;
//    private String district;
//    private String detail;
//    private String zipCode;

    @Embedded   // @Embeddable 클래스임을 명시
    @AttributeOverrides({
            @AttributeOverride(name = "city", column = @Column(name = "home_city")),
            @AttributeOverride(name = "district", column = @Column(name = "home_distirct")),
            @AttributeOverride(name = "detail", column = @Column(name = "home_address_detail")),
            @AttributeOverride(name = "zipCode", column = @Column(name = "home_zip_code")),
    })
    private Address homeAddress;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "city", column = @Column(name = "company_city")),
            @AttributeOverride(name = "district", column = @Column(name = "company_distirct")),
            @AttributeOverride(name = "detail", column = @Column(name = "company_address_detail")),
            @AttributeOverride(name = "zipCode", column = @Column(name = "company_zip_code")),
    })

    private Address companyAddress;
}
