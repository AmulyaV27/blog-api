package com.example.blog.blog.services;

import com.example.blog.blog.entities.Category;
import com.example.blog.blog.entities.Post;
import com.example.blog.blog.entities.PostResponse;
import com.example.blog.blog.entities.User;
import com.example.blog.blog.payloads.PostDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PostService {
    PostDTO createPost(PostDTO postDTO, long userId, long categoryId);
    PostDTO getPostById(Long id);
    PostResponse getAllPosts(Integer pageSize, Integer pageNumber, String sortBy, String sortDir);
    PostDTO updatePost(PostDTO postDTO);
    boolean deletePost(Long id);
    List<PostDTO> getPostByUser(Long userId);
    List<PostDTO> getPostByCategory(Long categoryId);
    List<PostDTO> searchPost(String title);
}
