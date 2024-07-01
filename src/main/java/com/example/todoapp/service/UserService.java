package com.example.todoapp.service;

import com.example.todoapp.DTO.LoginDTO;
import com.example.todoapp.DTO.response.UserDetailsResponse;
import com.example.todoapp.DTO.response.UserSignupResponse;
import com.example.todoapp.exception.UserException;
import com.example.todoapp.models.User;
import com.example.todoapp.repository.UserDao;
import com.example.todoapp.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User findByUserId(int userId) {
        return userDao.findByUserId(userId);
    }

    @Override
    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public UserDetailsResponse findUserDetailsByEmail(String email) {
        User user = userDao.findByEmail(email);
        return UserDetailsResponse
                .builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }

    @Override
    public UserSignupResponse addUser(User user) throws UserException{

        // check if user with email is already present
        User currentUser = userDao.findByEmail(user.getEmail());
        if (currentUser != null) {
            throw new UserException("Email already in use");
        }
        UserSignupResponse userSignupResponse = UserSignupResponse.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .build();

        String password = user.getPassword();
        user.setPassword(passwordEncoder.encode(password));
        userDao.save(user);

        return userSignupResponse;
    }

    @Override
    public User loginUser(LoginDTO loginDTO) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword())
        );
        return userDao.findByEmail(loginDTO.getEmail());
    }
}
