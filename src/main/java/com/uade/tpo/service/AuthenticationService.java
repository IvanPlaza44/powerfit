package com.uade.tpo.service;

import com.uade.tpo.controllers.auth.AuthenticationRequest;
import com.uade.tpo.controllers.auth.AuthenticationResponse;
import com.uade.tpo.controllers.auth.RegisterRequest;

public interface AuthenticationService {

    //Registrar un nuevo usuario
    AuthenticationResponse  registerUser(RegisterRequest request);

    //Loguearse
    AuthenticationResponse authenticate(AuthenticationRequest request);
}