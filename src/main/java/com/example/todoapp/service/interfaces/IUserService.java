package com.example.todoapp.service.interfaces;

import com.example.todoapp.DTO.LoginDTO;
import com.example.todoapp.DTO.response.UserDetailsResponse;
import com.example.todoapp.DTO.response.UserSignupResponse;
import com.example.todoapp.exception.UserException;
import com.example.todoapp.models.User;

import java.util.Optional;

public interface IUserService {
    public User findByUserId(int userId);
    public User findByEmail(String email);
    public UserDetailsResponse findUserDetailsByEmail(String email);
    public UserSignupResponse addUser(User user) throws UserException;
    public User loginUser(LoginDTO loginDTO);
}
