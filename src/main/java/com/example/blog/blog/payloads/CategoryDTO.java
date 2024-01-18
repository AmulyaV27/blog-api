package com.example.blog.blog.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CategoryDTO {

    private long id;
    @NotEmpty
    @Size(min=3,max=30,message = "category title should be min of 3 chars and max of 30 characters")
    private String categoryTitle;
    @NotEmpty
    private String categoryDescription;
}
