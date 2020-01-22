package io.swagslash.gametrackerserver.controller;

import io.swagslash.gametrackerserver.exception.ResourceNotFoundException;
import io.swagslash.gametrackerserver.model.User;
import io.swagslash.gametrackerserver.repository.UserRepository;
import io.swagslash.gametrackerserver.security.CurrentUser;
import io.swagslash.gametrackerserver.security.UserPrincipal;
import io.swagslash.gametrackerserver.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private UserRepository userRepository;

    private UserService userService;

    public UserController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @GetMapping("/me")
    @PreAuthorize("hasRole('USER')")
    public User getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
    }

    @PostMapping("/search")
    @PreAuthorize("hasRole('USER')")
    public List<String> searchForUser(@RequestBody String term) {
        return userService.findUsernamesByTerm(term);
    }
}
