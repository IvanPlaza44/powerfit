package com.uade.tpo.entity.dto;

import com.uade.tpo.entity.Role;
import lombok.Data;

@Data
public class UserRegistrationDTO {
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private Role role;
}