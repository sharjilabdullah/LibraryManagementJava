package com.example.LibraryManagement.service;

import com.example.LibraryManagement.dto.UserDTO;
import com.example.LibraryManagement.entity.User;
import com.example.LibraryManagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserDTO registerUser(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setRole(userDTO.getRole());
        user.setPassword(userDTO.getPassword());
        User registeredUser = userRepository.save(user);

        return new UserDTO(registeredUser.getId(),registeredUser.getName(),registeredUser.getEmail(),registeredUser.getPassword(),registeredUser.getRole() );
    }

    public Optional<UserDTO> findUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(user -> new UserDTO(user.getId(), user.getName(), user.getEmail(),user.getPassword() , user.getRole()));
    }
}
