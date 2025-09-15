package pl.com.ezdev.loreatlas.modules.user.api;

import lombok.Builder;

@Builder
public record UserResponse (
        Long id,
        String username,
        String email,
        String password,
        Role role
) {}
