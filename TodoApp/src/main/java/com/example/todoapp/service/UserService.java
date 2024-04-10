package com.example.todoapp.service;

import com.example.todoapp.dto.UserDTO;
import com.example.todoapp.entity.User;
import com.example.todoapp.exception.UserNotFoundException;
import com.example.todoapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                    .map(UserDTO::mapToDto)
                    .collect(Collectors.toList());
    }

    public UserDTO getUserById(Long userId) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));
        return UserDTO.mapToDto(existingUser);
    }


    public UserDTO updateUser(Long userId, UserDTO updatedUserDTO) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));
        existingUser.setId(userId);
        existingUser = updatedUserDTO.mapToEntity();
        existingUser = userRepository.save(existingUser);
        return UserDTO.mapToDto(existingUser);

    }

    public void deleteUser(Long userId) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));
        userRepository.delete(existingUser);

    }
}
