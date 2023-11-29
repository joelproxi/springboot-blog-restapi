package com.proxidev.springbootblogrestapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proxidev.springbootblogrestapi.entities.Post;

/**
 * PostRepositories
 */
public interface PostRepository extends JpaRepository<Post, Long> {
}