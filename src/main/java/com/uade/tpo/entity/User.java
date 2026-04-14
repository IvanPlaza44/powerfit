package com.uade.tpo.entity;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User implements UserDetails {

    
    //Identificador
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    //Username unico y obligatorio
    @Column(nullable = false, unique = true)
    private String username;

    //Contraseña obligatoria
    @Column(nullable = false)
    @JsonIgnore
    private String password;

    //Email obligatorio y unico
    @Column(nullable = false, unique = true)
    private String email;

    //Primer nombre obligatorio
    @Column(nullable = false)
    private String firstName;

    //Apellido no es obligatorio
    @Column
    private String lastName;

    //Rol deo usuario
    @Builder.Default
    @Enumerated(EnumType.STRING)
    private Role role = Role.BUYER;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

// package com.uade.tpo.entity;

// import com.fasterxml.jackson.annotation.JsonIgnore;

// import jakarta.persistence.Column;
// import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import jakarta.persistence.EnumType;
// import jakarta.persistence.Enumerated;
// import lombok.AllArgsConstructor;
// import lombok.Builder;
// import lombok.Data;
// import lombok.NoArgsConstructor;

// @Data
// @Builder
// @NoArgsConstructor
// @AllArgsConstructor
// @Entity
// public class User {
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     @Column(nullable = false, unique = true)
//     private String username;

//     @Column(nullable = false)
//     @JsonIgnore
//     private String password;

//     @Column(nullable = false, unique = true)
//     private String email;

//     @Column(nullable = false)
//     private String firstName;

//     @Column
//     private String lastName;

//     @Enumerated(EnumType.STRING)
//     private Role role;
// }

