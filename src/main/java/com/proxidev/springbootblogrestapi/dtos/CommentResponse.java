package com.proxidev.springbootblogrestapi.dtos;

import lombok.*;

import java.time.LocalDateTime;

@ToString
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentResponse {

    private Long id;
    private String body;
    private Long post;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
