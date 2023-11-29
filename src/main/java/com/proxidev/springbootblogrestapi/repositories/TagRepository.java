package com.proxidev.springbootblogrestapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proxidev.springbootblogrestapi.entities.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {
    Tag findByName(String tag);
}
