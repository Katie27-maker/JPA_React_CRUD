package com.lec.bt70_JPA_KANBAN.repository;

import com.lec.bt70_JPA_KANBAN.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String name);
}
