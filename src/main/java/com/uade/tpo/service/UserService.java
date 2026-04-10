package com.uade.tpo.service;

import com.uade.tpo.entity.User;
import com.uade.tpo.entity.dto.UserRequest;

public interface UserService {

    //Registrar un nuevo usuario
    User registerUser(UserRequest user);

    //Loguearse
    boolean authenticate(String username, String password);
}