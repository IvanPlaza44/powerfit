package com.uade.tpo.controllers.auth;

import com.uade.tpo.entity.Role;
import lombok.Data;

@Data
public class UserRequest {
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private Role role;
}