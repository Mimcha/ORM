package com.example.demo.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf().disable() // Отключение CSRF для упрощения тестирования
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/api/persons/city/**", "/api/persons/younger-than/**").permitAll() // Доступ без авторизации
                        .anyRequest().authenticated() // Все остальные endpoint'ы требуют авторизации
                )
                .formLogin(Customizer.withDefaults()) // Включение стандартной формы логина
                .logout(Customizer.withDefaults()) // Включение возможности выхода
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance(); // Используется для тестирования
    }
}