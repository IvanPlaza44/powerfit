package com.uade.tpo.service;

import org.springframework.stereotype.Service;

import com.uade.tpo.entity.Role;
import com.uade.tpo.exceptions.UserGenericException;
import com.uade.tpo.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {

    private final UserRepository repository;

    @Override
    public void becomeSeller(String username) {
        var user = repository.findByUsername(username)
                .orElseThrow(() -> new UserGenericException("Usuario no encontrado"));

        if (user.getRole() == Role.SELLER) {
            throw new UserGenericException("El usuario ya es vendedor");
        }

        user.setRole(Role.SELLER);
        repository.save(user);
    }
}