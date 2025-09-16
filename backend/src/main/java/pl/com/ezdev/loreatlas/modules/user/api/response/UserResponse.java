package pl.com.ezdev.loreatlas.modules.user.api.response;

import lombok.Builder;
import pl.com.ezdev.loreatlas.modules.user.domain.Role;

@Builder
public record UserResponse (
        Long id,
        String username,
        String email,
        String password,
        Role role
) {}
