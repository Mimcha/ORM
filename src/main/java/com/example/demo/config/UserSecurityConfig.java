package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserSecurityConfig {

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        var userDetailsService = new InMemoryUserDetailsManager();

        // Пользователь с ролью READ
        UserDetails reader = User.withUsername("reader")
                .password(passwordEncoder.encode("reader123"))
                .roles("READ")
                .build();

        // Пользователь с ролью WRITE
        UserDetails writer = User.withUsername("writer")
                .password(passwordEncoder.encode("writer123"))
                .roles("WRITE")
                .build();

        // Пользователь с ролью DELETE
        UserDetails deleter = User.withUsername("deleter")
                .password(passwordEncoder.encode("deleter123"))
                .roles("DELETE")
                .build();

        // Пользователь с ролями WRITE и DELETE
        UserDetails admin = User.withUsername("admin")
                .password(passwordEncoder.encode("admin123"))
                .roles("WRITE", "DELETE")
                .build();

        // Добавляем пользователей в менеджер
        userDetailsService.createUser(reader);
        userDetailsService.createUser(writer);
        userDetailsService.createUser(deleter);
        userDetailsService.createUser(admin);

        return userDetailsService;
    }
}