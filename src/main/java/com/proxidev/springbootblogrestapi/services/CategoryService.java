package com.proxidev.springbootblogrestapi.services;

import static com.proxidev.springbootblogrestapi.utils.AppConstant.CATEGORY;
import static com.proxidev.springbootblogrestapi.utils.AppConstant.ID;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.proxidev.springbootblogrestapi.entities.Category;
import com.proxidev.springbootblogrestapi.exceptions.ResourceNotFoundException;
import com.proxidev.springbootblogrestapi.repositories.CategoryRepository;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public ResponseEntity<Category> addCategory(Category category) {
        Category resp = categoryRepository.save(new Category(category.getName()));
        return new ResponseEntity<>(resp, HttpStatus.CREATED);
    }

    public ResponseEntity<List<Category>> getAllCategory() {
        List<Category> categories = categoryRepository.findAll();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    public ResponseEntity<Category> getCategory(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(CATEGORY, ID, id));
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    public ResponseEntity<Category> updateCategory(Long id, Category category) {
        Category data = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(CATEGORY, ID, id));
        data.setName(category.getName());
        var res = categoryRepository.save(data);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    public ResponseEntity<String> deleteCategory(Long id) {
        categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(CATEGORY, ID, id));
        return new ResponseEntity<>("success", HttpStatus.NO_CONTENT);
    }

}
