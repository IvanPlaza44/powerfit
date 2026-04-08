package com.uade.tpo.service;

import com.uade.tpo.entity.User;
import com.uade.tpo.entity.dto.UserRegistrationDTO;

public interface UserService {
    User registerUser(UserRegistrationDTO registrationDTO);
    boolean authenticate(String username, String password);
}