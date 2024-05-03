package com.lec.bt70_JPA_KANBAN.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    @JsonIgnore
    private String password;

    @Transient   // DB 에 반영 안함. (영속화 하지 않음)
    @ToString.Exclude  // toString() 결과에서 뺌.
    @JsonIgnore
    private String re_password;  // 비밀번호 확인 입력

    @ManyToMany(fetch = FetchType.EAGER)
    @ToString.Exclude
    @JsonIgnore
    private List<Authority> authorities;

    public void addAuthoriy(Authority... authorities){
        Collections.addAll(this.authorities, authorities);
    }   // 권한 변경

    private LocalDateTime delete_at;

    @PrePersist
    public void setAuthority(){
        this.authorities.add(new Authority(1L,"ROLE_MEMBER"));
    }
}
