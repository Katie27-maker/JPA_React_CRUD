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
        return userRepository.findByUsername(username);
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
    public List<Authority> selectAuthoritiesById(Long id) { // <- 이 id는 Authority의 id가 아닌 User의 id

        User user = userRepository.findById(id).orElse(null);
        // 아래 코드는 단순히 유저 권한만 찾아서 리턴해줌! => 없는 계정일때 null을 리턴하여 NPE가 발생!
        // ※ NPE => NullPointException 약자!!! 많이 사용됨

//        return user.getAuthorities();

        // 그래서 아래 코드로 진행
        if(user != null)    // <- 유저가 존재한다면!
            return user.getAuthorities(); // 권한 리스트를 반환!
        // 근데 없는 유저라면... 빈 배열을 반환하여 NPE이 발생 안함!!!
        return new ArrayList<>();
    }
}
