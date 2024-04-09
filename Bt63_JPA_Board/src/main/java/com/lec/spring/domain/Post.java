package com.lec.spring.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Model 객체
/**
 * DTO 객체
 *  : Data Transfer Object 라고도 함.
 *
 *  객체 -> DB
 *  DB -> 객체
 *  reg param -> 객체
 *  ..
 *
 */

// 웹개발시...
// 가능한, 다음 3가지는 이름을 일치시켜주는게 좋습니다.
// 클래스 필드명 = DB 필드명 = form의 name명

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "t8_post")
@DynamicInsert
@DynamicUpdate
public class Post extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // 글 id (PK)

    @Column(nullable = false)
    private String subject;

    @Column(columnDefinition = "LONGTEXT")
    private String content;

    @ColumnDefault(value = "0")
    private long viewCnt;

    @ManyToOne(optional = false)
    @ToString.Exclude
    private User user;  // 글 작성자 (FK)


    // 첨부파일
    @OneToMany(cascade = CascadeType.ALL) // cascade = CascadeType.ALL   삭제등의 동작 발생시 child 자동삭제
    @JoinColumn(name = "post_id")
    @ToString.Exclude
    @Builder.Default
    private List<Attachment> fileList = new ArrayList<>();

    // 댓글
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "post_id")
    @ToString.Exclude
    @Builder.Default
    private List<Comment> comments = new ArrayList<>();

    public void addComments(Comment... comments){
        Collections.addAll(this.comments, comments);
    }
}








