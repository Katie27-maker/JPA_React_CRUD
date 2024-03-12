package com.lec.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class MvcConfiguration {

    @Bean
    public PasswordEncoder encoder() {
        System.out.println("PasswordEncoder");
        return new BCryptPasswordEncoder();     // 회;원가입할 때 받아오는 비밀번호를 암호화 시켜줌
    }
}
