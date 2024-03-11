package com.lec.spring.service;

import com.lec.spring.domain.Post;
import com.lec.spring.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {


    @Autowired
    private PostRepository postRepository;  // final은 안 붙여두됨

    @Override
    public int write(Post post) {
        postRepository.save(post); // SELECT >  PK 없음  > INSERT
        return 1;
    }

    @Override
    public Post detail(Long id) {
        Post post = postRepository.findPostById(id);    // 이미 연속화가 되어 있기 때문에 postRepository를 안해도 됨
        System.out.println(post);
        if(post != null){
//            Long plus = ;
            post.setViewCnt(post.getViewCnt() +1);
            System.out.println(post);

        }


        return post;
    }

    @Override
    public List<Post> list() {

        return postRepository.findAll();
    }

    @Override
    public Post selectById(Long id) {
        return postRepository.findPostById(id);
    }

    @Override
    public int update(Post post) {
        postRepository.save(post);  // SELECT > PK 있으면 UPDATE
        Post result = postRepository.findPostById(post.getId());
        return post.equals(result) ? 0 : 1;   // 값이 변경이 되었냐? 아니면 변경이 되지 않았나?
    }

//    == 주소를 비교 똑같은 객체가 아니면 다른주소를 (안에 값이 같더라도 다른게 나옴 fals)


    @Override
    public int deleteById(Long id) {
        postRepository.deleteById(id);
        Post result = postRepository.findPostById(id);  // 게시물이 삭제하고 난 다음을 말하는거임 post아이디의 번호를 말하는게 아니라 삭제를 하고 난 다음에 result의 값이 0인지 아닌지에 따라서 삭제했는지 성공여부를 html에서 알려줌

        return result == null ? 1 : 0;  // null이냐 아니냐를 판단
    }
}