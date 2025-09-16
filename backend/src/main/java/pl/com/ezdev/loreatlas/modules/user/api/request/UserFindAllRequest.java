package pl.com.ezdev.loreatlas.modules.user.api.request;

import lombok.Builder;
import pl.com.ezdev.loreatlas.modules.user.domain.Role;

@Builder
public record UserFindAllRequest (
        String username,
        String email,
        Role role
    ) {}
