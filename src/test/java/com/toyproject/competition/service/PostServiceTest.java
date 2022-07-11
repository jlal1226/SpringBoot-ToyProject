package com.toyproject.competition.service;

import com.toyproject.competition.dto.PostDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class PostServiceTest {

    @Autowired
    private PostService postService;

    @Test
    public void 게시글_저장_테스트 () throws Exception {
        // given

        // when

        // then

    }

    @Test
    public void 게시글_수정_테스트 () throws Exception {
        // given
        String title = "title update";
        String content = "content update";
        PostDto postDto = PostDto.builder()
                .title(title)
                .content(content)
                .build();
        Long id = 1L;
        // when
        postService.updatePost(id, postDto);

        // then

    }

    @Test
    @Commit
    public void 게시글_삭제_테스트 () throws Exception {
        // given
        Long id = 2L;
        // when
        postService.deletePost(id);
        // then

    }

}