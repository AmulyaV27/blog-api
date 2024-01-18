package com.example.blog.blog.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private long id;
    @Size(min=4,message = "name should be minimum of 4 characters")
    @NotEmpty
    private String name;
    @Email(message = "provided email is invalid")
    private String email;
    @Size(min=4,message = "password should be minimum of 4 characters")
    @NotEmpty
    private String password;
    @NotEmpty
    private String about;
}
