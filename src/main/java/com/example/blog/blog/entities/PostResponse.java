package com.example.blog.blog.entities;

import com.example.blog.blog.payloads.PostDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
public class PostResponse {
    private List<PostDTO> posts;
    private int totalPages;
    private long totalElements;
    private int pageSize;
    private int pageNumber;
    private boolean lastPage;
}
