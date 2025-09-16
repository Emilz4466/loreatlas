package pl.com.ezdev.loreatlas.modules.user.api.request;

import pl.com.ezdev.loreatlas.modules.user.domain.Role;

public record UserPostRequest (
        String username,
        String email,
        Role role
    ) {
}
