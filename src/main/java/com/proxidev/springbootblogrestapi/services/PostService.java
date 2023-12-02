package com.proxidev.springbootblogrestapi.services;

import com.proxidev.springbootblogrestapi.dtos.PostRequestDto;
import com.proxidev.springbootblogrestapi.dtos.PostResponseDto;
import com.proxidev.springbootblogrestapi.entities.Category;
import com.proxidev.springbootblogrestapi.entities.Post;
import com.proxidev.springbootblogrestapi.entities.Tag;
import com.proxidev.springbootblogrestapi.exceptions.ResourceNotFoundException;
import com.proxidev.springbootblogrestapi.repositories.CategoryRepository;
import com.proxidev.springbootblogrestapi.repositories.PostRepository;
import com.proxidev.springbootblogrestapi.repositories.TagRepository;

import static com.proxidev.springbootblogrestapi.utils.AppConstant.*;
import static com.proxidev.springbootblogrestapi.utils.CheckerUtils.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;
    private final TagRepository tagRepository;

    public PostService(
            PostRepository postRepository,
            CategoryRepository categoryRepository,
            TagRepository tagRepository) {
        this.postRepository = postRepository;
        this.categoryRepository = categoryRepository;
        this.tagRepository = tagRepository;
    }

    public ResponseEntity<List<PostResponseDto>> getAllPost(Integer page, Integer size) {
        validatedPaginationData(page, size);
        var pageable = PageRequest.of(page, size, Sort.Direction.DESC, "createdAt");
        Page<Post> posts = postRepository.findAll(pageable);
        List<Post> content = posts.getNumberOfElements() == 0 ? Collections.emptyList() : posts.getContent();
        List<PostResponseDto> resp = content.stream().map(this::mapToPostResponseDto).toList();
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    public ResponseEntity<PostResponseDto> addPost(PostRequestDto postReq) {
        Category category = categoryRepository.findById(postReq.getCategory())
                .orElseThrow(() -> new ResourceNotFoundException(CATEGORY, ID, postReq.getCategory()));

        List<Tag> tags = new ArrayList<>(postReq.getTags().size());

        for (String item : postReq.getTags()) {
            Tag tag = tagRepository.findByName(item);
            tag = tag == null ? tagRepository.save(new Tag(item)) : tag;
            tags.add(tag);
        }
        var post = Post.builder()
                .title(postReq.getTitle())
                .body(postReq.getBody())
                .author(postReq.getAuthor())
                .category(category)
                .tags(tags)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        postRepository.save(post);

        return new ResponseEntity<>(this.mapToPostResponseDto(post), HttpStatus.CREATED);
    }

    public PostResponseDto getPostDetail(Long id) {
        Post post = postRepository.findById(id).orElse(null);
        return mapToPostResponseDto(post);
    }

    public ResponseEntity<PostResponseDto> updatePost(Long id, PostRequestDto req) {
        var post = postRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(POST, ID, req));
        var data = Post.builder()
                .title(req.getTitle())
                .body(req.getBody())
                .updatedAt(LocalDateTime.now())
                .author(post.getAuthor())
                .createdAt(post.getCreatedAt())
                .build();
        data.setId(post.getId());
        postRepository.save(data);
        return new ResponseEntity<>(mapToPostResponseDto(post), HttpStatus.OK);
    }

    public void deletePost(Long id) {
        postRepository.findById(id).ifPresent(postRepository::delete);
    }

    private PostResponseDto mapToPostResponseDto(Post post) {
        return PostResponseDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .body(post.getBody())
                .category(post.getCategory().getId())
                .tags(post.getTags().stream().map(tag -> tag.getName()).toList())
                .author(post.getAuthor())
                .createdAt(post.getCreatedAt())
                .updatedAt(post.getUpdatedAt())
                .build();
    }

}