package pl.com.ezdev.loreatlas.modules.auth.api.request;

import lombok.Builder;

@Builder
public record RegisterRequest(
        String username,
        String email,
        String password
) {
}