package com.example.blog.blog.controllers;

import com.example.blog.blog.payloads.CategoryDTO;
import com.example.blog.blog.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @PostMapping("/save")
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.categoryService.createCategory(categoryDTO));
    }
    @GetMapping("/all")
    public ResponseEntity<List<CategoryDTO>> getAllCategories(){
        return ResponseEntity.ok(this.categoryService.getAllCategories());
    }
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable("id") Long id){
        return ResponseEntity.ok(this.categoryService.getCategoryById(id));
    }
    @PutMapping("/update")
    public ResponseEntity<CategoryDTO> updateCategory(@Valid @RequestBody CategoryDTO CategoryDTO){
        return ResponseEntity.status(HttpStatus.OK).body(this.categoryService.updateCategory(CategoryDTO));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> updateCategory(@PathVariable("id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(this.categoryService.deleteCategory(id));
    }

}
