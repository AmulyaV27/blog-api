package com.example.blog.blog.services;

import com.example.blog.blog.payloads.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO createUser(UserDTO userDTO);
    List<UserDTO> getAllUsers();
    UserDTO getUserById(Long id);
    UserDTO updateUser(UserDTO userDTO);
    boolean deleteUser(Long id);

}
