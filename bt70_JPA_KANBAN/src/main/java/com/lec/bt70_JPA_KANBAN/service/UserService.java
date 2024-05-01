package com.lec.bt70_JPA_KANBAN.service;

import com.lec.bt70_JPA_KANBAN.domain.Authority;
import com.lec.bt70_JPA_KANBAN.domain.User;
import com.lec.bt70_JPA_KANBAN.repository.AuthorityRepository;
import com.lec.bt70_JPA_KANBAN.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor    // 인터페이스 대용
public class UserService {

    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public List<Authority> selectAuthoritiesById(Long id){
        return userRepository.findById(id).orElseThrow(()->new NullPointerException("에러 아님")).getAuthorities();
    };
}
