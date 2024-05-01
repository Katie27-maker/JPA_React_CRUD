package com.lec.bt70_JPA_KANBAN.repository;


import com.lec.bt70_JPA_KANBAN.domain.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {


}