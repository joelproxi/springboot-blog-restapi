package com.proxidev.springbootblogrestapi.services;

import com.proxidev.springbootblogrestapi.dtos.CommentRequest;
import com.proxidev.springbootblogrestapi.dtos.CommentResponse;
import com.proxidev.springbootblogrestapi.entities.Comment;
import com.proxidev.springbootblogrestapi.entities.Post;
import com.proxidev.springbootblogrestapi.repositories.CommentRepository;
import com.proxidev.springbootblogrestapi.repositories.PostRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public CommentService(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    public ResponseEntity<CommentResponse> addComment(Long postId, CommentRequest req) {
        Post post = postRepository.findById(postId).get();
        var comment = commentRepository.save(
                Comment.builder()
                        .body(req.getBody())
                        .createdAt(LocalDateTime.now())
                        .updatedAt(LocalDateTime.now())
                        .post(post)
                        .build());

        return new ResponseEntity<CommentResponse>(mapToCommentResponseDto(comment), HttpStatus.CREATED);
    }

    private CommentResponse mapToCommentResponseDto(Comment comment) {
        return CommentResponse.builder()
                .id(comment.getId())
                .body(comment.getBody())
                .post(comment.getPost().getId())
                .createdAt(comment.getCreatedAt())
                .updatedAt(comment.getUpdatedAt())
                .build();
    }

    public ResponseEntity<List<CommentResponse>> getAllComment(Long postId) {
        List<Comment> comments = commentRepository.findByPostId(postId);
        List<CommentResponse> resp = comments.stream().map(this::mapToCommentResponseDto).toList();
        return new ResponseEntity<List<CommentResponse>>(resp, HttpStatus.OK);
    }

    public ResponseEntity<CommentResponse> getComment(Long postId, Long id) {
        Comment comment = commentRepository.findById(id).get();

        return new ResponseEntity<CommentResponse>(mapToCommentResponseDto(comment), HttpStatus.OK);
    }

    public ResponseEntity<CommentResponse> updateComment(Long postId, Long id, CommentRequest req) {
        var comment = commentRepository.findById(id).get();
        if (Objects.equals(comment.getPost().getId(), postId)) {
            comment.setBody(req.getBody());
            commentRepository.save(comment);
            return new ResponseEntity<>(mapToCommentResponseDto(comment), HttpStatus.OK);
        }
        return null;
    }

    public ResponseEntity<String> deleteComment(Long postId, Long id) {
        Post post = postRepository.findById(postId).get();
        Comment comment = commentRepository.findById(id).get();
        if (Objects.equals(comment.getPost().getId(), post.getId())) {
            commentRepository.delete(comment);
            return new ResponseEntity<String>("Comment has deleted", HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<String>("Comment has not deleted", HttpStatus.BAD_REQUEST);
    }

}
