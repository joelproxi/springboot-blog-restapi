package com.proxidev.springbootblogrestapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proxidev.springbootblogrestapi.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
