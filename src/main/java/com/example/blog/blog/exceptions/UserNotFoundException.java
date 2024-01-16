package com.example.blog.blog.exceptions;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(long id){
        super("User not found for id: " +id);
    }
}
