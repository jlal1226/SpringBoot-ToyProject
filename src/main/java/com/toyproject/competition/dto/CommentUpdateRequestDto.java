package com.toyproject.competition.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentUpdateRequestDto {
    private Long postId;
    private Long commentId;
    private String comment;
}
