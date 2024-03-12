package com.lec.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing		// baseEntity 사용시 넣어야 할 어노테이션 없으면 도메인마다 날짜 쎃야함
public class Bt16JpaCrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(Bt16JpaCrudApplication.class, args);
	}

}