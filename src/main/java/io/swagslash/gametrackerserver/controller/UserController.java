package io.swagslash.gametrackerserver.controller;

import io.swagslash.gametrackerserver.exception.ResourceNotFoundException;
import io.swagslash.gametrackerserver.model.User;
import io.swagslash.gametrackerserver.repository.UserRepository;
import io.swagslash.gametrackerserver.security.CurrentUser;
import io.swagslash.gametrackerserver.security.UserPrincipal;
import io.swagslash.gametrackerserver.service.AgentTokenService;
import io.swagslash.gametrackerserver.service.AuthenticationService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/me")
    @PreAuthorize("hasRole('USER')")
    public User getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
    }
}
