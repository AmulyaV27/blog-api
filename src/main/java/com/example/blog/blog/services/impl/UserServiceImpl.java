package com.example.blog.blog.services.impl;

import com.example.blog.blog.entities.User;
import com.example.blog.blog.exceptions.UserNotFoundException;
import com.example.blog.blog.payloads.UserDTO;
import com.example.blog.blog.repositories.UserRepository;
import com.example.blog.blog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user=this.userRepository.save(this.dtoToUser(userDTO));
        return this.userToDTO(user);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return this.userRepository.findAll().stream().map(this::userToDTO).collect(Collectors.toList());

    }

    @Override
    public UserDTO getUserById(Long id) {
        User user=this.userRepository.findById(id).orElseThrow(()->new UserNotFoundException(id));
        return this.userToDTO(user);
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO) {
        User user=this.userRepository.findById(userDTO.getId()).orElseThrow(()->new UserNotFoundException(userDTO.getId()));
        user.setEmail(userDTO.getEmail());
        user.setName(userDTO.getName());
        user.setAbout(userDTO.getAbout());
        this.userRepository.save(user);
        return this.userToDTO(user);

    }

    @Override
    public boolean deleteUser(Long id) {
        User user=this.userRepository.findById(id).orElseThrow(()->new UserNotFoundException(id));
        this.userRepository.deleteById(user.getId());
        return true;
    }
    private User dtoToUser(UserDTO userDTO){
        User user = new User();
        user.setName(userDTO.getName());
        user.setAbout(userDTO.getAbout());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        return user;
    }
    private UserDTO userToDTO(User user){
        UserDTO userDTO=new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setAbout(user.getAbout());
        userDTO.setEmail(user.getEmail());
        return userDTO;
    }


}
