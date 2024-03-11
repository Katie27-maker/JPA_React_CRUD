package com.lec.spring.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

@Component
public class UserValidator implements Validator {

    @Autowired
    UserService userService;

    public boolean supports(Class<?> clazz) {
        System.out.printf("supports(" + clazz.getName() + ")");
        boolean result = User.class.isAssignableFrom(clazz);
        System.out.printf(result);
        return result;
    }

    public void validate(Object target, Errors errors) {
        User user = (User)target;

        String username = user.getUsername();
        if(username == null || username.trim().isEmpty()) {
            errors.rejectValue("username", "username 은 필수입니다");

        } else if (userService.isExist(username)) {
//             이미 등록된 중복된 아이디(username) 이 들어오면
            errors.rejectValue("username", "이미 존재하는 아이디(username) 입니다");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name은 필수입니다");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password 는 필수입니다");

        if(!user.getPassword().equals(user.getRe_password())){
            errors.rejectValue("re_password", "비밀번호와 비밀번호확인 입력값은 같아야 합니다.");
        }
    }

    public boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
    }
}