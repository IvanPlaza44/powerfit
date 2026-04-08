package com.uade.tpo.service;

import com.uade.tpo.entity.User;
import com.uade.tpo.entity.dto.UserRegistrationDTO;
import com.uade.tpo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User registerUser(UserRegistrationDTO registrationDTO) {
        User user = new User();
        // Corregido: ahora usamos registrationDTO que es el nombre del parámetro
        user.setUsername(registrationDTO.getUsername());
        user.setEmail(registrationDTO.getEmail());
        user.setPassword(registrationDTO.getPassword()); 
        user.setFirstName(registrationDTO.getFirstName());
        user.setLastName(registrationDTO.getLastName());
        user.setRole(registrationDTO.getRole());
        
        return userRepository.save(user);
    }

    @Override
    public boolean authenticate(String username, String password) {
        return userRepository.findByUsername(username)
                .map(user -> user.getPassword().equals(password))
                .orElse(false);
    }
}