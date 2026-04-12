package com.uade.tpo.controllers.auth;

import com.uade.tpo.entity.User;
import com.uade.tpo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody UserRequest user) {
        return ResponseEntity.ok(userService.registerUser(user));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) {
        if (userService.authenticate(username, password)) {
            return ResponseEntity.ok("Login exitoso");
        }
        return ResponseEntity.status(401).body("Usuario o contraseña incorrectos");
    }
}