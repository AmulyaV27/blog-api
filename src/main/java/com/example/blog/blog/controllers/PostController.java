package com.example.blog.blog.controllers;

import com.example.blog.blog.AppConstants;
import com.example.blog.blog.entities.PostResponse;
import com.example.blog.blog.payloads.PostDTO;
import com.example.blog.blog.services.FileService;
import com.example.blog.blog.services.PostService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PostController {
    @Autowired
    private PostService postService;
    @Autowired
    private FileService fileService;
    @Value("${project.image}")
    private String path;
    @PostMapping("/user/{userId}/category/{categoryId}/save")
    public ResponseEntity<PostDTO> createPost(@PathVariable("userId") Long userId, @PathVariable("categoryId") Long categoryId, @RequestBody PostDTO postDTO){
        PostDTO post = this.postService.createPost(postDTO,userId,categoryId);
        return ResponseEntity.status(HttpStatus.CREATED).body(post);
    }
    @GetMapping("/all")
    public ResponseEntity<PostResponse> getAllPosts(@RequestParam(defaultValue = AppConstants.PAGE_SIZE,required = false)Integer pageSize,@RequestParam(defaultValue = AppConstants.PAGE_NUMBER,required = false)Integer pageNumber, @RequestParam(defaultValue = AppConstants.SORT_BY, required = false) String sortBy, @RequestParam(defaultValue = AppConstants.SORT_DIR, required = false) String sortDir){
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
    @PostMapping("/{postId}/image/upload")
    public ResponseEntity<PostDTO> uploadImage(@PathVariable Long postId, @RequestParam("image") MultipartFile image) throws IOException, IOException {
        String imageName= this.fileService.uploadImage(this.path,image);
        PostDTO postDTO=this.postService.getPostById(postId);
        postDTO.setImageName(imageName);
        PostDTO updatedPost=this.postService.updatePost(postDTO);
        return ResponseEntity.status(HttpStatus.OK).body(updatedPost);
    }
    @GetMapping(value = "/image/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE )
    public void downloadImage(@PathVariable String imageName, HttpServletResponse response) throws IOException {
        InputStream resource=this.fileService.getImage(path,imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource,response.getOutputStream());
    }
}
