package com.uade.tpo.service;

import com.uade.tpo.controllers.auth.UserRequest;
import com.uade.tpo.entity.User;
import com.uade.tpo.exceptions.UserGenericException;
import com.uade.tpo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    //Registrar un nuevo usuario
    @Override
    public User registerUser(UserRequest request) {

        //Validamos si ya existe el username ya que es unico
        if(userRepository.existsByUsername(request.getUsername())){
            throw new UserGenericException("El nombre de usuario '" + request.getUsername() + "' ya existe.");
        }

        //Validamos que el email ya esta en uso porque es unico.
        if(userRepository.existsByEmail(request.getEmail())){
            throw new UserGenericException("El email " + request.getEmail() + "ya esta en uso, pruebe con uno nuevo");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword()); 
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setRole(request.getRole());
  
        return userRepository.save(user);
    }

    //Loguearse
    @Override
    public boolean authenticate(String username, String password) {
        return userRepository.findByUsername(username)
                .map(user -> user.getPassword().equals(password))
                .orElse(false);
    }
}