package com.example.blog.blog.services.impl;

import com.example.blog.blog.entities.Category;
import com.example.blog.blog.exceptions.CategoryNotFoundException;
import com.example.blog.blog.payloads.CategoryDTO;
import com.example.blog.blog.repositories.CategoryRepository;
import com.example.blog.blog.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category= this.modelMapper.map(categoryDTO,Category.class);
        this.categoryRepository.save(category);
        return this.modelMapper.map(category,CategoryDTO.class);
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        return this.categoryRepository.findAll().stream().map(c->this.modelMapper.map(c,CategoryDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDTO getCategoryById(long id) {
        Category category= this.categoryRepository.findById(id).orElseThrow(()-> new CategoryNotFoundException(id));
        return this.modelMapper.map(category,CategoryDTO.class);
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO) {
        Category category= this.categoryRepository.findById(categoryDTO.getId()).orElseThrow(()-> new CategoryNotFoundException(categoryDTO.getId()));
        category.setCategoryTitle(categoryDTO.getCategoryTitle());
        category.setCategoryDescription(categoryDTO.getCategoryDescription());
        return this.modelMapper.map(this.categoryRepository.save(category),CategoryDTO.class);
    }

    @Override
    public boolean deleteCategory(long id) {
        Category category=this.categoryRepository.findById(id).orElseThrow(()->new CategoryNotFoundException(id));
        this.categoryRepository.deleteById(id);
        return true;
    }
}
