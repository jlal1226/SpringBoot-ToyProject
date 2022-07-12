package com.toyproject.competition.controller;

import com.toyproject.competition.dto.CommentRequestDto;
import com.toyproject.competition.dto.PostDto;
import com.toyproject.competition.dto.PostResponseDto;
import com.toyproject.competition.repository.PostRepository;
import com.toyproject.competition.service.CommentService;
import com.toyproject.competition.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {

    private final PostService postService;
    private final CommentService commentService;
    private final PostRepository postRepository;

    // 게시판 목록
    @GetMapping("/list")
    public String postList(Model model, @PageableDefault(size = 10, sort = "createdDate", direction = Sort.Direction.DESC) Pageable pageable) {
        postService.postList(pageable);

        model.addAttribute("paging", postRepository.findAll(pageable));
        System.out.println("=================================");
        return "pages/post";
    }

    // 게시글 입력 페이지
    @GetMapping("/write")
    public String postForm(Model model) {
        model.addAttribute("postDto", new PostDto());
        return "postForm";
    }

    // 게시글 저장
    @PostMapping("/save")
    public String savePost(@Valid PostDto postDto, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {

            model.addAttribute("postDto", postDto);

            Map<String, String> validatorResult = postService.validateHandling(bindingResult);
            for (String key : validatorResult.keySet()) {
                model.addAttribute(key, validatorResult.get(key));
            }
            return "postForm";
        }


        postService.savePost(postDto);

        return "redirect:/post/list";
    }

    // 게시글 상세 내용
    @GetMapping("/view")
    public String postView(Model model, @RequestParam(value = "id", required = false) Long postId) {
        if (postId == null) {
            System.out.println("check====================");
            model.addAttribute("post", postService.getPostView(postId));
            model.addAttribute("commentRequestDto", new CommentRequestDto());
            return "pages/postView";
        }
        model.addAttribute("post", postService.getPostView(postId));
        model.addAttribute("commentList", postService.getComment(postId));
        model.addAttribute("commentRequestDto", new CommentRequestDto());
        System.out.println("post controller");
        return "pages/postView";
    }

    // 게시글 수정
    @GetMapping("/update")
    public String updatePostView(Model model, @RequestParam(value = "id", required = false) Long id) {
        model.addAttribute("post", postService.getPostView(id));
        return "pages/postEdit";
    }


    @PostMapping("/update")
    public String updatePostView(@RequestParam(value = "id", required = false) Long id, PostDto postDto) {
        postService.updatePost(id, postDto);
        return "redirect:/post/list";
    }

    // 게시글 삭제
    @GetMapping("/delete")
    public String deletePost(@RequestParam(value = "id", required = false) Long id) {
        postService.deletePost(id);
        System.out.println(id + " deleted");
        return "redirect:/post/list";
    }
}
