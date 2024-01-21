package com.example.blog.blog.repositories;

import com.example.blog.blog.entities.Category;
import com.example.blog.blog.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {

    List<Post> findByCategoryId(Long categoryId);
    List<Post> findByUserId(Long userId);
    List<Post> findByTitleContainingIgnoreCase(String title);
}
