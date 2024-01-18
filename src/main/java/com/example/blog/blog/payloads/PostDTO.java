package com.example.blog.blog.payloads;

import com.example.blog.blog.entities.Category;
import com.example.blog.blog.entities.User;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PostDTO {

    private long id;
    private String title;
    private String content;
    private String imageName;
    private String createdDate;
    private CategoryDTO category;
    private UserDTO user;
}
