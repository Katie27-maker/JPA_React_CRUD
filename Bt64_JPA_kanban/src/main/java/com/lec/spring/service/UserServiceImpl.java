package com.lec.spring.service;

import com.lec.spring.domain.Authority;
import com.lec.spring.domain.User;
import com.lec.spring.repository.AuthorityRepository;
import com.lec.spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    // username(회원 아이디) 로 User 정보 읽어오기
    @Override
    public User findByUsername(String username) {
        System.out.println("유저이름" + username);
        return userRepository.findByUsername(username);
    }

    // 특정 username(회원 아이디) 의 회원이 존재하는지 확인
    @Override
    public boolean isExist(String username) {
        User user = findByUsername(username);
        return (user != null) ? true : false;
    }

    // 신규회원 등록
    @Override
    public int register(User user) {
        // DB 에는 회원 username 을 대문자로 저장
        user.setUsername(user.getUsername().toUpperCase());

        // password 는 암호화 해서 저장.  PasswordEncoder 객체 사용
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        // 새로운 회원 (User) 저장.  id 값 받아옴.
        user = userRepository.saveAndFlush(user);  // INSERT

        // 신규회원은 ROLE_MEMBER 권한을 부여하기
        Authority auth = authorityRepository.findByName("ROLE_MEMBER");
        user.addAuthoriy(auth);
        userRepository.save(user);  // INSERT

        return 1;
    }


    // 특정 사용자(id) 의 authority(들)
    @Override
    public List<Authority> selectAuthoritiesById(Long id) {
        User user = userRepository.findById(id).orElse(null);

        if(user != null)
            return user.getAuthorities();

        return new ArrayList<>();
    }
}