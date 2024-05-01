package com.lec.bt70_JPA_KANBAN.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="cards")
public class Cards {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long card_id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contnet;

    private LocalDateTime deadline;

    @Column(nullable = false)
    private LocalDateTime created_at;

    @Column(nullable = false)
    private LocalDateTime modified_at;

    private LocalDateTime deleted_at;

    @ManyToOne
    private Colums column_id;

    @ManyToOne
    private User user_id;


}
