package com.toyproject.competition.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentUpdateResponseDto {
    private Long postId;
    private Long commentId;
    private String comment;
}
