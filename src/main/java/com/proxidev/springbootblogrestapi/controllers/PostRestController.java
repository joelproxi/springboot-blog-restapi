package com.proxidev.springbootblogrestapi.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.proxidev.springbootblogrestapi.dtos.PostRequestDto;
import com.proxidev.springbootblogrestapi.dtos.PostResponseDto;
import com.proxidev.springbootblogrestapi.services.PostService;

@RestController
@RequestMapping(value = "/api/v1/posts")
public class PostRestController {
    private final PostService postService;

    public PostRestController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public ResponseEntity<List<PostResponseDto>> getAllPost() {
        return postService.getAllPost();
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public PostResponseDto getPostDetail(@PathVariable Long id) {
        return postService.getPostDetail(id);
    }

    @PostMapping
    public ResponseEntity<PostResponseDto> addPost(@RequestBody PostRequestDto post) {
        return postService.addPost(post);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<PostResponseDto> updatePost(@PathVariable Long id, @RequestBody PostRequestDto post) {
        return postService.updatePost(id, post);

    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deletePost(@PathVariable Long id) {
        postService.deletePost(id);
    }
}
