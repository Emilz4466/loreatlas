package pl.com.ezdev.loreatlas.auth.api;

import lombok.Builder;

@Builder
public record RegisterRequest(
        String username,
        String email,
        String password
) {
}