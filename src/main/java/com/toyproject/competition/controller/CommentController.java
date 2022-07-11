package com.toyproject.competition.controller;

import com.toyproject.competition.domain.Comment;
import com.toyproject.competition.dto.CommentRequestDto;
import com.toyproject.competition.dto.PostDto;
import com.toyproject.competition.service.CommentService;
import com.toyproject.competition.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;
    private final PostService postService;

    /**
     * 댓글 저장
     */
    @PostMapping("/write")
    public String writeComment(@Valid CommentRequestDto commentRequestDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {

            model.addAttribute("commentRequestDto", commentRequestDto);

            Map<String, String> validatorResult = commentService.validateHandling(bindingResult);
            for (String key : validatorResult.keySet()) {
                model.addAttribute(key, validatorResult.get(key));
            }
            return "";
        }
        System.out.println(commentRequestDto.getComment());
        commentService.saveComment(commentRequestDto);
        model.addAttribute("commentList", postService.getComment(commentRequestDto.getPostId()));

        return "pages/postView ::#list";
    }
}
