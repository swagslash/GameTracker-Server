package io.swagslash.gametrackerserver.service;

import io.swagslash.gametrackerserver.exception.BadRequestException;
import io.swagslash.gametrackerserver.payload.AuthResponse;
import io.swagslash.gametrackerserver.payload.LoginRequest;
import io.swagslash.gametrackerserver.payload.SignUpRequest;

public interface AuthenticationService {

    AuthResponse registerUser(SignUpRequest request) throws BadRequestException;

    AuthResponse loginUser(LoginRequest request) throws BadRequestException;

}
