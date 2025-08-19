package com.example.apiRest.controllers;

import java.util.Optional;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.apiRest.dto.UserLoginDTO;
import com.example.apiRest.dto.UserRegisterDTO;
import com.example.apiRest.dto.UserResponseDTO;
import com.example.apiRest.model.UserModel;
import com.example.apiRest.services.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/auth")

public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@RequestBody UserRegisterDTO dto) {
        UserModel user = new UserModel();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());

        UserModel newUser = userService.registerUser(user);

        UserResponseDTO response = new UserResponseDTO(newUser.getId(), newUser.getUsername(), newUser.getEmail());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserLoginDTO dto) {
        Optional<UserModel> existingUser = userService.findByUsername(dto.getUsername());

        if (existingUser.isPresent() &&
                userService.checkPassword(dto.getPassword(), existingUser.get().getPassword())) {
            return ResponseEntity.ok("Login realizado com sucesso!");
        }

        return ResponseEntity.status(401).body("Usuário ou senha inválidos");
    }

}
