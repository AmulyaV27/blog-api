package com.example.blog.blog.services.impl;

import com.example.blog.blog.entities.Category;
import com.example.blog.blog.entities.Post;
import com.example.blog.blog.entities.User;
import com.example.blog.blog.exceptions.CategoryNotFoundException;
import com.example.blog.blog.exceptions.PostNotFoundException;
import com.example.blog.blog.exceptions.UserNotFoundException;
import com.example.blog.blog.payloads.CategoryDTO;
import com.example.blog.blog.payloads.PostDTO;
import com.example.blog.blog.repositories.CategoryRepository;
import com.example.blog.blog.repositories.PostRepository;
import com.example.blog.blog.repositories.UserRepository;
import com.example.blog.blog.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
    public PostDTO getPostById(Long id) {
        Post post = this.postRepository.findById(id).orElseThrow(()-> new PostNotFoundException(id));
        return this.modelMapper.map(post,PostDTO.class);
    }

    @Override
    public List<PostDTO> getAllPosts() {
        return this.postRepository.findAll().stream().map(c -> this.modelMapper.map(c, PostDTO.class)).toList();
    }

    @Override
    public PostDTO updatePost(PostDTO postDTO) {
        Post post = this.postRepository.findById(postDTO.getId()).orElseThrow(()-> new PostNotFoundException(postDTO.getId()));
        post.setImageName(postDTO.getImageName());
        post.setContent(postDTO.getContent());
        post.setTitle(postDTO.getTitle());
        return this.modelMapper.map(this.postRepository.save(post),PostDTO.class);
    }

    @Override
    public boolean deletePost(Long id) {
        Post post = this.postRepository.findById(id).orElseThrow(()-> new PostNotFoundException(id));
        this.postRepository.delete(post);
        return true;
    }

    @Override
    public List<PostDTO> getPostByUser(Long userId) {
        return this.postRepository.findByUserId(userId).stream().map(e->this.modelMapper.map(e,PostDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<PostDTO> getPostByCategory(Long categoryId) {
        return this.postRepository.findByCategoryId(categoryId).stream().map(e->this.modelMapper.map(e, PostDTO.class)).collect(Collectors.toList());

    }

    @Override
    public List<PostDTO> searchPost(String title) {
        return this.postRepository.findByTitleContainingIgnoreCase(title).stream().map(e->this.modelMapper.map(e,PostDTO.class)).collect(Collectors.toList());

    }
}
