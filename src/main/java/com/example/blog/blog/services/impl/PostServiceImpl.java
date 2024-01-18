package com.example.blog.blog.services.impl;

import com.example.blog.blog.entities.Category;
import com.example.blog.blog.entities.Post;
import com.example.blog.blog.entities.User;
import com.example.blog.blog.exceptions.CategoryNotFoundException;
import com.example.blog.blog.exceptions.UserNotFoundException;
import com.example.blog.blog.payloads.PostDTO;
import com.example.blog.blog.repositories.CategoryRepository;
import com.example.blog.blog.repositories.PostRepository;
import com.example.blog.blog.repositories.UserRepository;
import com.example.blog.blog.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public PostDTO createPost(PostDTO postDTO, long userId,long categoryId) {
        Post post=this.modelMapper.map(postDTO,Post.class);

        User user=this.userRepository.findById(userId).orElseThrow(()->new UserNotFoundException(userId));
        Category category=this.categoryRepository.findById(categoryId).orElseThrow(()->new CategoryNotFoundException(categoryId));
        post.setUser(user);
        post.setImageName("img1.png");
        post.setCategory(category);
        return this.modelMapper.map(this.postRepository.save(post),PostDTO.class);
    }

    @Override
    public Post getPostById(long id) {
        return null;
    }

    @Override
    public List<Post> getAllPosts() {
        return null;
    }

    @Override
    public Post updatePost(PostDTO postDTO) {
        return null;
    }

    @Override
    public boolean deletePost(long id) {
        return false;
    }

    @Override
    public List<Post> getPostByUser(User user) {
        return null;
    }

    @Override
    public List<Post> getPostByCategory(Category category) {
        return null;
    }

    @Override
    public List<Post> searchPost(String title) {
        return null;
    }
}
