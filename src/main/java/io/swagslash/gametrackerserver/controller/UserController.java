package io.swagslash.gametrackerserver.controller;

import io.swagslash.gametrackerserver.exception.ResourceNotFoundException;
import io.swagslash.gametrackerserver.model.User;
import io.swagslash.gametrackerserver.repository.UserRepository;
import io.swagslash.gametrackerserver.security.CurrentUser;
import io.swagslash.gametrackerserver.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public User getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
    }
}