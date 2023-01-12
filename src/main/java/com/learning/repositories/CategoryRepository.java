package com.learning.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.models.CategoryModel;

public interface CategoryRepository extends JpaRepository<CategoryModel, Integer> {
    Page<CategoryModel> findByNameContains(String name, Pageable pageable);
}
