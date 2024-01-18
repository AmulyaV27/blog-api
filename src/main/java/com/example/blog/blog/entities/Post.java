package com.example.blog.blog.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Table(name="post")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String content;
    private String imageName;
    private Date createdDate;
    @ManyToOne
    private Category category;
    @ManyToOne
    private User user;

    @PrePersist
    public void prePersist(){
        this.createdDate= new Date();
    }


}
