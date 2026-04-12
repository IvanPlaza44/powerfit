package com.uade.tpo.service;

import com.uade.tpo.controllers.auth.UserRequest;
import com.uade.tpo.entity.User;

public interface UserService {

    //Registrar un nuevo usuario
    User registerUser(UserRequest user);

    //Loguearse
    boolean authenticate(String username, String password);
}