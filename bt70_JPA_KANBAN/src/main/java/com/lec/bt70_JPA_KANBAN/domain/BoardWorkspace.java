package com.lec.bt70_JPA_KANBAN.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="boardWorkspace")
public class BoardWorkspace {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long bm_id;

    @ManyToOne(optional = false)    // 보드가 하나,
    private Board board_id;

    @ManyToOne(optional = false)    // 보드가 하나, 유저가 여러명
    private User user_id;
}
