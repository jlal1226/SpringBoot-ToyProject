package com.toyproject.competition.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostResponseDto {
    private Long postId;
    private String title;
    private String username;
}
