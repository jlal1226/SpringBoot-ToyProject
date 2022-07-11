package com.toyproject.competition.dto;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class PostViewResponseDto {
    private Long id;
    private String username;
    private Timestamp date;
    private String title;
    private String content;
}
