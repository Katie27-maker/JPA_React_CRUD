package com.lec.bt70_JPA_KANBAN.service;

import com.lec.bt70_JPA_KANBAN.domain.Authority;
import com.lec.bt70_JPA_KANBAN.domain.User;
import com.lec.bt70_JPA_KANBAN.repository.AuthorityRepository;
import com.lec.bt70_JPA_KANBAN.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

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

    public boolean login(User user) {
        User loginUser = userRepository.findByUsernameAndPassword(user.getUsername(),user.getPassword()).orElseThrow(()->new RuntimeException("로그인 실패"));
        Jedis jedis = new Jedis("localhost");

            jedis.set(loginUser.getUsername(),loginUser.getPassword());
            jedis.close();
            return true;
    }

    public boolean signup(User user) {
//        회원 가입으로 입력한 유저 닉네임을 기준으로 서버에서 유저를 찾아 발견되지 않으면 저장! 발견되면 중복으로 회원가입 실패 보냄
        if(null == userRepository.findByUsername(user.getUsername())){
            userRepository.save(user);
            return true;    // 가입 성공
        }else
            return false;   // 가입 실패
    }
}
