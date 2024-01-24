package com.example.blog.blog.controllers;

import com.example.blog.blog.AppConstants;
import com.example.blog.blog.entities.PostResponse;
import com.example.blog.blog.payloads.PostDTO;
import com.example.blog.blog.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PostController {
    @Autowired
    private PostService postService;
    @PostMapping("/user/{userId}/category/{categoryId}/save")
    public ResponseEntity<PostDTO> createPost(@PathVariable("userId") Long userId, @PathVariable("categoryId") Long categoryId, @RequestBody PostDTO postDTO){
        PostDTO post = this.postService.createPost(postDTO,userId,categoryId);
        return ResponseEntity.status(HttpStatus.CREATED).body(post);
    }
    @GetMapping("/all")
    public ResponseEntity<PostResponse> getAllPosts(@RequestParam(defaultValue = AppConstants.PAGE_SIZE,required = false)Integer pageSize,
                                                    @RequestParam(defaultValue = AppConstants.PAGE_NUMBER,required = false)Integer pageNumber,
                                                    @RequestParam(defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
                                                    @RequestParam(defaultValue = AppConstants.SORT_DIR, required = false) String sortDir){
        return ResponseEntity.ok(this.postService.getAllPosts(pageSize,pageNumber,sortBy,sortDir));
    }
    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable("id") Long id){
        return ResponseEntity.ok(this.postService.getPostById(id));
    }
    @PutMapping("/update")
    public ResponseEntity<PostDTO> updatePost(@RequestBody PostDTO postDTO){
        return ResponseEntity.ok(this.postService.updatePost(postDTO));

    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deletePost(@PathVariable("id") Long id){
        return ResponseEntity.ok(this.postService.deletePost(id));
    }
    @GetMapping("/category/{id}")
    public ResponseEntity<List<PostDTO>>getByCategory(@PathVariable("id") Long categoryId){
        return ResponseEntity.ok(this.postService.getPostByCategory(categoryId));

    }
    @GetMapping("/user/{id}")
    public ResponseEntity<List<PostDTO>>getByUser(@PathVariable("id") Long userId){
        return ResponseEntity.ok(this.postService.getPostByUser(userId));

    }
    @GetMapping("/search/{title}")
    public ResponseEntity<List<PostDTO>>searchPost(@PathVariable("title") String title){
        return ResponseEntity.ok(this.postService.searchPost(title));

    }
}
