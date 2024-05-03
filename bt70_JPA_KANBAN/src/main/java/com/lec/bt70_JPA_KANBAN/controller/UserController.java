package com.lec.bt70_JPA_KANBAN.controller;

import com.lec.bt70_JPA_KANBAN.domain.User;
import com.lec.bt70_JPA_KANBAN.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor    // 위에랑 세트
@RequestMapping("/")
public class UserController {

    private final UserService userService;

    @PostMapping ("/login")
    public String login(@RequestBody User user) {
        // 레디스 연결

        if (userService.login(user)){
            return "로그인 성공";
        } else {
           return "로그인 실패";
        }
    }

    @PostMapping("/SignUp")
    public String SignUp(@RequestBody User user) {

        if(userService.signup(user)){
            return "회원 가입 성공";
        } else return "회원 가입 실패";
    }
}
