package com.proxidev.springbootblogrestapi.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proxidev.springbootblogrestapi.entities.Category;
import com.proxidev.springbootblogrestapi.services.CategoryService;

@RestController
@RequestMapping(value = "/api/v1/categories")
public class CategoryRestController {

    private final CategoryService categoryService;

    public CategoryRestController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<Category> addCategory(@RequestBody Category category) {
        return categoryService.addCategory(category);
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategory() {
        return categoryService.getAllCategory();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Category> getCategory(@PathVariable Long id) {
        return categoryService.getCategory(id);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category category) {
        return categoryService.updateCategory(id, category);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id){
        return categoryService.deleteCategory(id);
    }
}
