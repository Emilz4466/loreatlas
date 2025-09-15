package pl.com.ezdev.loreatlas.modules.user.api;

import lombok.Builder;

@Builder
public record UserFindAllRequest (
        String username,
        String email,
        Role role
    ) {}
