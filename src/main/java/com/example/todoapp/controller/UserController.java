package com.example.todoapp.controller;

import com.example.todoapp.DTO.LoginDTO;
import com.example.todoapp.DTO.response.LoginResponse;
import com.example.todoapp.DTO.response.UserDetailsResponse;
import com.example.todoapp.DTO.response.UserSignupResponse;
import com.example.todoapp.models.User;
import com.example.todoapp.service.JwtService;
import com.example.todoapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtService jwtService;

    @PostMapping(value = "/signup")
    public ResponseEntity<UserSignupResponse> signupUser(@RequestBody User user) {
        UserSignupResponse userSignupResponse = userService.addUser(user);
        return new ResponseEntity<>(userSignupResponse, HttpStatus.CREATED);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<LoginResponse> loginUser(@RequestBody LoginDTO loginDTO) {
        User authenticatedUser = userService.loginUser(loginDTO);
        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = LoginResponse.builder()
                .token(jwtToken)
                .build();

        return new ResponseEntity<>(loginResponse, HttpStatus.OK);
    }

    @GetMapping(value = "/{email}")
    public ResponseEntity<UserDetailsResponse> getUserByEmail(@PathVariable String email) {
        UserDetailsResponse userDetailsResponse = userService.findUserDetailsByEmail(email);
        return new ResponseEntity<>(userDetailsResponse, HttpStatus.OK);
    }
}
