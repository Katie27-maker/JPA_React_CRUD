package com.lec.spring.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "TBL_POST")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String subject;

//    @Column(length = 1000)
    @Column(
            // ↓ ddl-auto: update 에선 동작 안함.
            columnDefinition = "LONGTEXT"
    )
    private String content;

    @ColumnDefault(value = "0")
    private long viewCnt;

    private LocalDateTime regDate;

    @Column(nullable = false)
    private String user;

}
