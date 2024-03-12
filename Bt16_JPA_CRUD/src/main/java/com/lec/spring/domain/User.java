package com.lec.spring.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "TBL_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    @JsonIgnore
    private String password;
    @ToString.Exclude  // toString() 결과에서 뺌.
    @JsonIgnore
    private String re_password;  // 비밀번호 확인 입력
    private String name;
    private String email;

    @JsonIgnore
    private LocalDateTime regDate;

//     OAuth2
    private String provider;   // 어떤 OAuth2 제공자? Kakao, Naver, Google....
    private String providerId;  // provider 내에서의 고유 id 값
}
