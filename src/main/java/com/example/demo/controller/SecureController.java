package com.example.demo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.annotation.Secured;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/secure")
public class SecureController {

    @GetMapping("/read")
    @Secured("ROLE_READ")
    public Map<String, String> readAccess() {
        return Collections.singletonMap("message", "You have READ access!");
    }

    @GetMapping("/write")
    @RolesAllowed("ROLE_WRITE")
    public Map<String, String> writeAccess() {
        return Collections.singletonMap("message", "You have WRITE access!");
    }

    @GetMapping("/write-or-delete")
    @PreAuthorize("hasRole('ROLE_WRITE') or hasRole('ROLE_DELETE')")
    public Map<String, String> writeOrDeleteAccess() {
        return Collections.singletonMap("message", "You have either WRITE or DELETE access!");
    }

    @GetMapping("/check-username")
    public Map<String, String> checkUsername(@RequestParam String username) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();

        if (!currentUsername.equals(username)) {
            throw new IllegalArgumentException("Username does not match authenticated user!");
        }

        return Collections.singletonMap("message", "Username matches authenticated user!");
    }
}