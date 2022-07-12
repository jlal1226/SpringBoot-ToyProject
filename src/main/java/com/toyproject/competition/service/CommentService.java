package com.toyproject.competition.service;

import com.toyproject.competition.domain.Comment;
import com.toyproject.competition.domain.Member;
import com.toyproject.competition.domain.Post;
import com.toyproject.competition.dto.CommentRequestDto;
import com.toyproject.competition.dto.CommentResponseDto;
import com.toyproject.competition.repository.CommentRepository;
import com.toyproject.competition.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {

    private final CommentRepository commentRepository;
    private final MemberService memberService;
    private final PostRepository postRepository;

    /**
     * 댓글 등록
     */
    @Transactional
    public void saveComment(CommentRequestDto dto) {
        // 회원 조회
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        UserDetails userDetails = (UserDetails) principal;
        String username = userDetails.getUsername();

        Member member = memberService.findMember(username);

        Post post = postRepository.findById(dto.getPostId()).get();

        Comment comment = Comment.builder()
                .comment(dto.getComment())
                .build();
        comment.setPost(post);
        comment.setMember(member);

        commentRepository.save(comment);
    }

    /**
     * 댓글 조회
     */
    public List<CommentResponseDto> getCommentAll(Long postId) {
        List<Comment> commentList = commentRepository.findByPostId(postId);
        List<CommentResponseDto> dtoList = new ArrayList<>();
        for (Comment comment : commentList) {
            CommentResponseDto dto = CommentResponseDto.builder()
                    .comment(comment.getComment())
                    .date(comment.getModifiedDate())
                    .username(comment.getMember().getUsername())
                    .build();
            dtoList.add(dto);
        }
        return dtoList;
    }

    /**
     * 댓글 수정
     */
    @Transactional
    public void updateComment() {

    }

    /**
     * 댓글 삭제
     */
    @Transactional
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }

    /**
     * 유효성 처리 메서드
     */
    public Map<String, String> validateHandling(BindingResult bindingResult) {
        Map<String, String> validatorResult = new HashMap<>();

        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            String validKeyName = String.format("valid_%s", fieldError.getField());
            validatorResult.put(validKeyName, fieldError.getDefaultMessage());
        }
        return validatorResult;
    }

}
