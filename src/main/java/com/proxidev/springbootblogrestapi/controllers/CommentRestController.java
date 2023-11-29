package com.proxidev.springbootblogrestapi.controllers;

import com.proxidev.springbootblogrestapi.dtos.CommentRequest;
import com.proxidev.springbootblogrestapi.dtos.CommentResponse;
import com.proxidev.springbootblogrestapi.services.CommentService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/v1/posts/{postId}/comments")
public class CommentRestController {
    private final CommentService commentService;

    public CommentRestController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public ResponseEntity<CommentResponse> createComment(
            @PathVariable Long postId,
            @RequestBody CommentRequest req) {
        return commentService.addComment(postId, req);
    }

    @GetMapping
    public ResponseEntity<List<CommentResponse>> getAllComment(@PathVariable Long postId) {
        return commentService.getAllComment(postId);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CommentResponse> getComment(
            @PathVariable Long postId,
            @PathVariable Long id) {

        return commentService.getComment(postId, id);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CommentResponse> updateComment(
            @PathVariable Long postId, @PathVariable Long id,
            @RequestBody CommentRequest commentRequest) {
        return commentService.updateComment(postId, id, commentRequest);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteComment(
            @PathVariable Long postId, @PathVariable Long id) {
        return commentService.deleteComment(postId, id);
    }

}