package com.uade.tpo.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.uade.tpo.controllers.auth.AuthenticationRequest;
import com.uade.tpo.controllers.auth.AuthenticationResponse;
import com.uade.tpo.controllers.auth.RegisterRequest;
import com.uade.tpo.controllers.config.JwtService;
import com.uade.tpo.entity.Role;
import com.uade.tpo.entity.User;
import com.uade.tpo.exceptions.UserGenericException;
import com.uade.tpo.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService{
        private final UserRepository repository;
        private final PasswordEncoder passwordEncoder;
        private final JwtService jwtService;
        private final AuthenticationManager authenticationManager;

        //Registrar un nuevo usuario
        @Override
        public AuthenticationResponse registerUser(RegisterRequest request) {

                //Validamos si ya existe el username ya que es unico
                if(repository.existsByUsername(request.getUserName())){
                throw new UserGenericException("El nombre de usuario " + request.getUserName() + " ya existe.");
                }


                //Validamos que el email ya esta en uso porque es unico.
                if(repository.existsByEmail(request.getEmail())){
                throw new UserGenericException("El email " + request.getEmail() + " ya esta en uso, pruebe con uno nuevo");
                }


                var user = User.builder()
                                .firstName(request.getFirstName())
                                .lastName(request.getLastName())
                                .email(request.getEmail())
                                .password(passwordEncoder.encode(request.getPassword()))
                                .role(Role.BUYER)
                                .username(request.getUserName())
                                .build();

                repository.save(user);
                var jwtToken = jwtService.generateToken(user);
                return AuthenticationResponse.builder()
                                .accessToken(jwtToken)
                                .build();
        }


        //Loguearse
        @Override
        public AuthenticationResponse authenticate(AuthenticationRequest request) {
                authenticationManager.authenticate(
                                new UsernamePasswordAuthenticationToken(
                                                request.getUserName(),
                                                request.getPassword()));

                var user = repository.findByUsername(request.getUserName())
                                .orElseThrow(() -> new UserGenericException("Usuario no encontrado"));
                var jwtToken = jwtService.generateToken(user);
                return AuthenticationResponse.builder()
                                .accessToken(jwtToken)
                                .build();
        }
}
