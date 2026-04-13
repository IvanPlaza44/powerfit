package com.uade.tpo.controllers.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.uade.tpo.entity.Role;

import lombok.RequiredArgsConstructor;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

        private final JwtAuthenticationFilter jwtAuthFilter;
        private final AuthenticationProvider authenticationProvider;

        @Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .csrf(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests(req -> req

            // endpoints públicos (login / register)
            .requestMatchers("/api/v1/auth/**").permitAll()

            // manejo de errores
            .requestMatchers("/error/**").permitAll()

            // VER productos y categorías (cualquiera)
            .requestMatchers(org.springframework.http.HttpMethod.GET, "/products/**").permitAll() // cualquiera puede ver productos
            .requestMatchers(org.springframework.http.HttpMethod.GET, "/categories/**").permitAll() // cualquiera puede ver categorías

            // ADMIN maneja productos y categorías
            .requestMatchers("/products/**").hasAuthority(Role.ADMIN.name()) // crear, editar, borrar productos
            .requestMatchers("/categories/**").hasAuthority(Role.ADMIN.name()) // crear, editar categorías

            // usuarios agregar a favorites, ver o eliminar
            .requestMatchers("/favorite/**").hasAuthority(Role.BUYER.name()) // solo usuarios logueados manejan favoritos

            // usuarios gestionar y ver su cart
            .requestMatchers("/cart/**").hasAuthority(Role.BUYER.name()) // manejar carrito
            .requestMatchers("/cart-detail/**").hasAuthority(Role.BUYER.name()) // items del carrito

            // usuarios ver sus purchase
            .requestMatchers("/purchase/**").hasAnyAuthority(Role.BUYER.name(), Role.ADMIN.name()) // ver/comprar
            .requestMatchers("/purchase-detail/**").hasAnyAuthority(Role.BUYER.name(), Role.ADMIN.name()) // detalle de compras

            // todo lo demás con autenticacion
            .anyRequest().authenticated()
        )
        .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
        .authenticationProvider(authenticationProvider)
        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

    return http.build();
}

}
