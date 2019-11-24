package io.swagslash.gametrackerserver.service.impl;

import io.swagslash.gametrackerserver.config.AppProperties;
import io.swagslash.gametrackerserver.enums.AuthProvider;
import io.swagslash.gametrackerserver.enums.ResponseError;
import io.swagslash.gametrackerserver.exception.BadRequestException;
import io.swagslash.gametrackerserver.model.User;
import io.swagslash.gametrackerserver.payload.AuthResponse;
import io.swagslash.gametrackerserver.payload.LoginRequest;
import io.swagslash.gametrackerserver.payload.SignUpRequest;
import io.swagslash.gametrackerserver.repository.UserRepository;
import io.swagslash.gametrackerserver.security.TokenProvider;
import io.swagslash.gametrackerserver.service.AuthenticationService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private UserRepository userRepository;

    private AuthenticationManager authenticationManager;

    private PasswordEncoder passwordEncoder;

    private AppProperties appProperties;

    private TokenProvider tokenProvider;

    public AuthenticationServiceImpl(UserRepository userRepository, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, AppProperties appProperties, TokenProvider tokenProvider) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.appProperties = appProperties;
        this.tokenProvider = tokenProvider;
    }

    @Override
    public AuthResponse registerUser(SignUpRequest request) {
        if(emailExists(request.getEmail())) {
            throw new BadRequestException("Email already in Use", ResponseError.email_already_in_use);
        }

        User user = createUser(request);
        String token = getTokenForRequest(request);

        return new AuthResponse(token, user.getUsername(), appProperties.getAuth().getTokenExpirationMsec(), user.getEmail());
    }

    @Override
    public AuthResponse loginUser(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail()).get();
        String token = getTokenForRequest(request);

        return new AuthResponse(token, user.getUsername(), appProperties.getAuth().getTokenExpirationMsec(), user.getEmail());
    }

    private String getTokenForRequest(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return tokenProvider.createToken(authentication);
    }

    private boolean emailExists(String email) {
        return userRepository.existsByEmail(email);
    }

    private User createUser(SignUpRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setProvider(AuthProvider.local);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }
}
