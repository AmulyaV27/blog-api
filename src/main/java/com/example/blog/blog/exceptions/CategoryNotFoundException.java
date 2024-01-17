package com.example.blog.blog.exceptions;

public class CategoryNotFoundException extends RuntimeException{
    public CategoryNotFoundException(long id){
        super("category not found for id: "+id);
    }
}
