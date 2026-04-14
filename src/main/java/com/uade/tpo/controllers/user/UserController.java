package com.uade.tpo.controllers.user;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uade.tpo.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/become-seller")
    public ResponseEntity<String> becomeSeller(Authentication authentication) {

        String username = authentication.getName();

        userService.becomeSeller(username);

        return ResponseEntity.ok("Ahora sos vendedor 🎉");
    }
}
