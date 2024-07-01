package com.example.todoapp.DTO.response;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserSignupResponse {
    private String firstName;
    private String lastName;
    private String email;
}
