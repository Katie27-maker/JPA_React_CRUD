package com.lec.spring.service;

import com.lec.spring.domain.*;
import com.lec.spring.repository.CommentRepository;
import com.lec.spring.repository.PostRepository;
import com.lec.spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;

    // 특정 글(id) 의 댓글 목록
    @Override
    public QryCommentList list(Long id) {
        QryCommentList list = new QryCommentList();
        Post post = postRepository.findById(id).orElseThrow(()->(new NullPointerException("에러아님")));
        List<Comment> comments = commentRepository.findByPost(post.getId());  // TODO

        list.setCount(comments.size());   // 댓글의 개수
        list.setList(comments);
        list.setStatus("OK");

        return list;
    }

    // 특정 글 (postId) 에 특정 사용자(userId) 가 댓글 작성
    @Override
    public QryResult write(Long postId, Long userId, String content) {

        User user = userRepository.findById(userId).orElseThrow(()->new NullPointerException("에러 아님")); // TODO

        Comment comment = Comment.builder()
                .user(user)
                .content(content)
                .post(postId)
                .build();

        // TODO : INSERT
        commentRepository.saveAndFlush(comment);

        QryResult result = QryResult.builder()
                .count(1)
                .status("OK")
                .build();

        return result;
    }

    // 특정 댓글(id) 삭제
    @Override
    public QryResult delete(Long id) {

        //TODO : 존재하는지 여부 확인하고 삭제
//        commentRepository.delete(commentRepository.findById(id).orElseThrow(()->new NullPointerException("없는 댓글 입니다.")));
       Comment comment = commentRepository.findById(id).orElse(null);
//       comment변수에 찾은 댓글을 대입 만약, 댓글이 없을 경우 null을 대입

        int count = 0;  // 존재하는지 여부 확인하고 삭제
        String status = "FAIL";

        if(comment != null){
            commentRepository.delete(comment);  // DELETE
            count = 1;
            if(count > 0) status = "OK";
        }

        QryResult result = QryResult.builder()
                .count(count)
                .status(status)
                .build();

        return result;
    }
}














