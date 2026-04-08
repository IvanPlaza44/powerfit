package com.uade.tpo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    // Constructor vacío necesario para JPA
    public User() {
    }

    // Constructor manual útil para el Service
    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    private String firstName;
    private String lastName;

    @Enumerated(EnumType.STRING)
    private Role role;
}

