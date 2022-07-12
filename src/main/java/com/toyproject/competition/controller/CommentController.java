package com.toyproject.competition.controller;

import com.toyproject.competition.domain.Comment;
import com.toyproject.competition.dto.*;
import com.toyproject.competition.service.CommentService;
import com.toyproject.competition.service.PostService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final CommentService commentService;
    private final PostService postService;

    /**
     * 댓글 저장
     */
    @PostMapping("/write")
    public String writeComment(@Valid CommentRequestDto commentRequestDto, Model model) {
/*        if (bindingResult.hasErrors()) {
            model.addAttribute("commentRequestDto", commentRequestDto);

            Map<String, String> validatorResult = commentService.validateHandling(bindingResult);
            for (String key : validatorResult.keySet()) {
                model.addAttribute(key, validatorResult.get(key));
            }
            System.out.println("comment controller fail");
            return "";
        }*/
        Long postId = commentRequestDto.getPostId();
        // 댓글 저장
        commentService.saveComment(commentRequestDto);
/*
        List<CommentResponseDto> list = postService.getComment(commentRequestDto.getPostId());
        logger.error("list size {}", list.size());
        model.addAttribute("commentList", list);

*//*        ModelAndView view = new ModelAndView("pages/postView :: #list"); //  :: #list
        view.addObject("commentList", list);
        view.addObject("post", postService.getPostView(postId));
        view.addObject("commentRequestDto", new CommentRequestDto());*//*
        model.addAttribute("post", postService.getPostView(postId));
        model.addAttribute("commentRequestDto", new CommentRequestDto());*/
        return "redirect:/post/view?id=" + postId;
    }

    // 댓글 수정
    @GetMapping("/update")
    public String update(@RequestParam Long id, Model model) {
        Comment comment = commentService.getComment(id);
        CommentUpdateRequestDto dto = CommentUpdateRequestDto.builder()
                .postId(comment.getPost().getId())
                .commentId(comment.getId())
                .comment(comment.getComment())
                .build();

        model.addAttribute("commentForm", dto);

        return "pages/commentForm";
    }

    @PostMapping("/update")
    public String updateComment(@RequestParam Long id, CommentUpdateResponseDto dto) {
        Comment comment = commentService.getComment(id);
        commentService.updateComment(id, dto.getComment());
        return "redirect:/post/view?id=" + comment.getPost().getId();
    }

    // 댓글 삭제
    @GetMapping("/delete")
    public String delete(@RequestParam Long id) {
        Comment comment = commentService.getComment(id);
        commentService.deleteComment(id);
        String url = "redirect:/post/view?id=" + comment.getPost().getId();
        return url;
    }
}
