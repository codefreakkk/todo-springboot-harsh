package com.example.todoapp.DTO.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailsResponse {
    private String firstName;
    private String lastName;
    private String email;
    private Date createdAt;
    private Date updatedAt;
}
