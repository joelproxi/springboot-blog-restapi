package com.proxidev.springbootblogrestapi.dtos;

import lombok.*;

import java.time.LocalDateTime;

@ToString
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentRequest {
    private String body;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
