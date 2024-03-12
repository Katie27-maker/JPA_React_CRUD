package com.lec.spring.service;

import com.lec.spring.domain.Authority;
import com.lec.spring.domain.User;
import com.lec.spring.repository.AuthorityRepository;
import com.lec.spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
        return null;
    }

    // 특정 username(회원 아이디) 의 회원이 존재하는지 확인
    @Override
    public boolean isExist(String username) {
        return false;
    }

//    신규 회원 등록
    @Override
    public int register(User user) {
        user.setUsername(user.getUsername().toUpperCase());

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        user = userRepository.saveAndFlush(user);

        Authority auth = authorityRepository.findByName("ROLE_MEMVER");
        user.addAuthoriy(auth);
        userRepository.save(user);

        return 1;
    }

//    특정 사용자(id) 의 authority(들)
    @Override
    public List<Authority> selectAuthoritiesById(Long id) {
        return null;
    }
}
