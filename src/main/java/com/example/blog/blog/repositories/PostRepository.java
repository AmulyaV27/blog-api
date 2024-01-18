package com.example.blog.blog.repositories;

import com.example.blog.blog.entities.Category;
import com.example.blog.blog.entities.Post;
import com.example.blog.blog.entities.User;
import com.example.blog.blog.payloads.CategoryDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {

    List<Post> findByCategory(Category category);
    List<Post> findByUser(User user);
    List<Post> findByTitleContainingIgnoreCase(String title);
}
