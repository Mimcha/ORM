package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/api/persons/city/**", "/api/persons/younger-than/**").permitAll() // Доступ без авторизации
                        .anyRequest().authenticated() // Все остальные endpoint'ы требуют авторизации
                )
                .formLogin(Customizer.withDefaults()) // Включение стандартной формы логина
                .logout(Customizer.withDefaults()) // Включение возможности выхода
                .build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        PasswordEncoder passwordEncoder = NoOpPasswordEncoder.getInstance();
        // Создание пользователей для тестирования
        var userDetailsService = new InMemoryUserDetailsManager();
        userDetailsService.createUser(
                org.springframework.security.core.userdetails.User.withUsername("user")
                        .password(passwordEncoder.encode("password")) // Пароль без хеширования
                        .roles("USER")
                        .build()
        );
        userDetailsService.createUser(
                org.springframework.security.core.userdetails.User.withUsername("admin")
                        .password(passwordEncoder.encode("adminpassword"))
                        .roles("ADMIN")
                        .build()
        );
        return userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance(); // Используется для тестирования
    }
}