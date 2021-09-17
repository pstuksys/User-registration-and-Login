package com.example.projektukas.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class AppUserRegistrationDto {
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
}
