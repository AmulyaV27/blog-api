package com.example.blog.blog.services;

import com.example.blog.blog.payloads.CategoryDTO;

import java.util.List;

public interface CategoryService {
    CategoryDTO createCategory(CategoryDTO categoryDTO);
    List<CategoryDTO> getAllCategories();
    CategoryDTO getCategoryById(long id);
    CategoryDTO updateCategory(CategoryDTO categoryDTO);
    boolean deleteCategory(long id);
}
