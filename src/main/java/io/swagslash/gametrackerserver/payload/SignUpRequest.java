package io.swagslash.gametrackerserver.payload;

import javax.validation.constraints.NotBlank;

public class SignUpRequest extends LoginRequest{
    @NotBlank
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}