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
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception { // Dejado como 'http'
            http
                .cors(cors -> cors.configurationSource(request -> {
                        var corsConfiguration = new org.springframework.web.cors.CorsConfiguration();
                        corsConfiguration.setAllowedOrigins(java.util.List.of("http://localhost:5173")); // Puerto de React
                        corsConfiguration.setAllowedMethods(java.util.List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
                        corsConfiguration.setAllowedHeaders(java.util.List.of("*"));
                        corsConfiguration.setAllowCredentials(true);
                        return corsConfiguration;
                    }))
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req -> req

                    // endpoints públicos (login / register)
                    .requestMatchers("/auth/**").permitAll()

                    // manejo de errores
                    .requestMatchers("/error/**").permitAll()

                    // VER productos y categorías (cualquiera)
                    .requestMatchers(org.springframework.http.HttpMethod.GET, "/products/**").permitAll() 
                    .requestMatchers(org.springframework.http.HttpMethod.GET, "/categories/**").permitAll() 

                    // SELLER maneja productos y categorías
                    .requestMatchers("/products/**").hasAuthority(Role.SELLER.name()) 
                    .requestMatchers("/categories/**").hasAuthority(Role.SELLER.name()) 

                    // usuarios agregar a favorites, ver o eliminar
                    .requestMatchers("/favorite/**").hasAuthority(Role.BUYER.name()) 

                    // usuarios gestionar y ver su cart
                    .requestMatchers("/cart/**").hasAuthority(Role.BUYER.name()) 
                    .requestMatchers("/cart-detail/**").hasAuthority(Role.BUYER.name()) 

                    // usuarios ver sus purchase
                    .requestMatchers("/purchase/**").hasAnyAuthority(Role.BUYER.name(), Role.ADMIN.name()) 
                    .requestMatchers("/purchase-detail/**").hasAnyAuthority(Role.BUYER.name(), Role.ADMIN.name()) 

                    // todo lo demás con autenticacion
                    .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

            return http.build(); // Retorna 'http.build()' correctamente
        }
}