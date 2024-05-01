package com.lec.bt70_JPA_KANBAN.domain;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="colums")
public class Colums {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String title;

    private LocalDateTime deleted_at;

    @ManyToOne(optional = false)    // notnull+빈값이 들어가면 안딤
    @JoinColumn(name = "board_id")
    @Column(nullable = false)
    private Board board;
}
