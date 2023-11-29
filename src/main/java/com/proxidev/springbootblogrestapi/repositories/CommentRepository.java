package com.proxidev.springbootblogrestapi.repositories;

import com.proxidev.springbootblogrestapi.entities.Comment;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    public List<Comment> findByPostId(Long postId);
}
