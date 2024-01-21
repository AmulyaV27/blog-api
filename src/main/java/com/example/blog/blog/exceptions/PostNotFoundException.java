package com.example.blog.blog.exceptions;

public class PostNotFoundException extends RuntimeException{
    public PostNotFoundException(Long id){
        super("Post not found for id: "+id);
    }
}
