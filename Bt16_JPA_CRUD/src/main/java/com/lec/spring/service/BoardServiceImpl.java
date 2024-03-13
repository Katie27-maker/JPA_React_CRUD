package com.lec.spring.service;

import com.lec.spring.domain.Post;
import com.lec.spring.repository.PostRepository;
import com.lec.spring.repository.UserRepository;
import com.lec.spring.user.U;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {


    @Value("${app.pagination.page_rows}")
    private int PAGE_ROWS;

    @Value("${app.pagination.write_pages}")
    private int WRITE_PAGES;

    @Autowired
    private PostRepository postRepository;  // final은 안 붙여두됨
    @Autowired
    private UserRepository userRepository;

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
    public List<Post> list(Integer page, Model model) {
        // 현재 페이지 parameter
        if(page == null) page = 1;  // 디폴트는 1page
        if(page < 1) page = 1;

        // 페이징
        // writePages: 한 [페이징] 당 몇개의 페이지가 표시되나
        // pageRows: 한 '페이지'에 몇개의 글을 리스트 할것인가?
        HttpSession session = U.getSession();
        Integer writePages = (Integer)session.getAttribute("writePages");
        if(writePages == null) writePages = WRITE_PAGES;  // 만약 session 에 없으면 기본값으로 동작
        Integer pageRows = (Integer)session.getAttribute("pageRows");
        if(pageRows == null) pageRows = PAGE_ROWS;  // 만약 session 에 없으면 기본값으로 동작

        // 현재 페이지 번호 -> session 에 저장
        session.setAttribute("page", page);

        // TODO : JPA 를 활용한 페이징 처리  --> Page<E>

        long cnt = 0; // 글 목록 전체의 개수  // TODO
        int totalPage = 0;  // 총 몇 '페이지' ?  // TODO

        // [페이징] 에 표시할 '시작페이지' 와 '마지막페이지'
        int startPage = 0;
        int endPage = 0;

        // 해당 페이지의 글 목록
        List<Post> list = null;

        if(cnt > 0){  // 데이터가 최소 1개 이상 있는 경우만 페이징
            //  page 값 보정
            if(page > totalPage) page = totalPage;

            // 몇번째 데이터부터 fromRow
            int fromRow = (page - 1) * pageRows;

            // [페이징] 에 표시할 '시작페이지' 와 '마지막페이지' 계산
            startPage = (((page - 1) / writePages) * writePages) + 1;
            endPage = startPage + writePages - 1;
            if (endPage >= totalPage) endPage = totalPage;

            // 해당페이지의 글 목록 읽어오기
            list = null; // TODO
            model.addAttribute("list", list);
        } else {
            page = 0;
        }

        model.addAttribute("cnt", cnt);  // 전체 글 개수
        model.addAttribute("page", page); // 현재 페이지
        model.addAttribute("totalPage", totalPage);  // 총 '페이지' 수
        model.addAttribute("pageRows", pageRows);  // 한 '페이지' 에 표시할 글 개수

        // [페이징]
        model.addAttribute("url", U.getRequest().getRequestURI());  // 목록 url
        model.addAttribute("writePages", writePages); // [페이징] 에 표시할 숫자 개수
        model.addAttribute("startPage", startPage);  // [페이징] 에 표시할 시작 페이지
        model.addAttribute("endPage", endPage);   // [페이징] 에 표시할 마지막 페이지

        return list;
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