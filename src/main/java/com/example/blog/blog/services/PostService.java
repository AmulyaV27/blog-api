package com.example.blog.blog.services;

import com.example.blog.blog.entities.Category;
import com.example.blog.blog.entities.Post;
import com.example.blog.blog.entities.User;
import com.example.blog.blog.payloads.PostDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PostService {
    PostDTO createPost(PostDTO postDTO, long userId, long categoryId);
    Post getPostById(long id);
    List<Post> getAllPosts();
    Post updatePost(PostDTO postDTO);
    boolean deletePost(long id);
    List<Post> getPostByUser(User user);
    List<Post> getPostByCategory(Category category);
    List<Post> searchPost(String title);
}
