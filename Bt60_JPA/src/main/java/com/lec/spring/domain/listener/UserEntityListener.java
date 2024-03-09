package com.lec.spring.domain.listener;

import com.lec.spring.domain.User;
import com.lec.spring.domain.UserHistory;
import com.lec.spring.repository.UserHistoryRepository;
import com.lec.spring.support.BeanUtils;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;

//@Component   // <-- Listener 에선 스프링 빈 주입 불가
public class UserEntityListener {

//    @Autowired   // <-- Listener 에선 스프링 빈 주입 불가
//    private UserHistoryRepository userHistoryRepository;

    @PostUpdate   // User 가 UPDATE 수행한 직후 호출
    @PostPersist   // User 가 INSERT 수행한 직후 호출
    public void postUpdateAndPersist(Object o){
        System.out.println(">> UserEntityListener#postUpdateAndPersist()");

        // Listener 에서 스프링 빈 객체 받아오기
        UserHistoryRepository userHistoryRepository = BeanUtils.getBean(UserHistoryRepository.class);

        User user = (User)o;

        // UserHistory 에 UPDATE 될 User 정보를 담아서 저장 (INSERT)
        UserHistory userHistory = new UserHistory();
        userHistory.setName(user.getName());
        userHistory.setEmail(user.getEmail());

        userHistoryRepository.save(userHistory); // INSERT
    }



}












