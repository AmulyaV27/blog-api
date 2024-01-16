package com.example.blog.blog.services.impl;

import com.example.blog.blog.entities.User;
import com.example.blog.blog.exceptions.UserNotFoundException;
import com.example.blog.blog.payloads.UserDTO;
import com.example.blog.blog.repositories.UserRepository;
import com.example.blog.blog.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
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

        return this.modelMapper.map(userDTO,User.class);

    }
    private UserDTO userToDTO(User user){
        return this.modelMapper.map(user,UserDTO.class);
    }


}
