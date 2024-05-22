package com.lec.bt75_JPA_ShoppingMall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class Bt75JpaShoppingMallApplication {

    public static void main(String[] args) {
        SpringApplication.run(Bt75JpaShoppingMallApplication.class, args);

        Test test = new Test();
        System.out.println("요기당" + test.name);
    }


}

class Test {
    String name = "주희";

    void hello(){
        System.out.println("안녕");
    }
}