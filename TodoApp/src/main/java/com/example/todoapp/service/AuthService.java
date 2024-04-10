package com.example.todoapp.service;

import com.example.todoapp.dto.LoginDTO;
import com.example.todoapp.dto.UserDTO;
import com.example.todoapp.entity.User;
import com.example.todoapp.repository.UserRepository;
import com.example.todoapp.util.JwtTokenUtil;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final JwtTokenUtil jwtTokenUtil;

    @Autowired
    public AuthService(UserRepository userRepository, JwtTokenUtil jwtTokenUtil) {
        this.userRepository = userRepository;
        this.jwtTokenUtil = jwtTokenUtil;
    }


    public String login(LoginDTO loginDTO){
        Optional<User> userOptional = userRepository.findByEmailAndPassword(loginDTO.getEmail(), loginDTO.getPassword());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            String token = jwtTokenUtil.generateToken(user);
            return token;
        } else {
            throw new EntityNotFoundException("User not found with provided credentials: " + loginDTO.getEmail());
        }
    }

    public UserDTO registerUser(UserDTO userDTO) {

        User user = userDTO.mapToEntity();
        user.setPassword(userDTO.getPassword());

        user = userRepository.save(user);

        return UserDTO.mapToDto(user);
    }
}
