package com.lec.bt70_JPA_KANBAN.repository;

import com.lec.bt70_JPA_KANBAN.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String name);

    Optional <User> findByUsernameAndPassword(String username, String password);


}
