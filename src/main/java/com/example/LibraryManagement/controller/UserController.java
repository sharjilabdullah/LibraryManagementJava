package com.example.LibraryManagement.controller;

import com.example.LibraryManagement.dto.UserDTO;
import com.example.LibraryManagement.entity.User;
import com.example.LibraryManagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@RequestBody UserDTO userDTO){
        UserDTO registeredUser = userService.registerUser(userDTO);
        return ResponseEntity.ok(registeredUser);
    }

    @GetMapping("/user")
    public ResponseEntity<UserDTO> getUserByEmail(@RequestParam String email){
        Optional<UserDTO> user = userService.findUserByEmail(email);
        return user.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
    }

    //TODO: Role based authentication and only admin can register a user(Learn Spring Security)
}
