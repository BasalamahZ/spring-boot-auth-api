package com.learning.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.learning.models.CategoryModel;
import com.learning.repositories.CategoryRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CategoryService {
    
    @Autowired
    private CategoryRepository categoryRepository;

    public CategoryModel save(CategoryModel category) {
        return categoryRepository.save(category);
    }

    public Iterable<CategoryModel> findAll() {
        return categoryRepository.findAll();
    }

    public CategoryModel findOne(int id) {
        return categoryRepository.findById(id).get();
    }

    public Iterable<CategoryModel> findByName(String name, Pageable pageable){
        return categoryRepository.findByNameContains(name, pageable);
    }
}
