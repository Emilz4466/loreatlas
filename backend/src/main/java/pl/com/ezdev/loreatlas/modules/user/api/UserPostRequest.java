package pl.com.ezdev.loreatlas.modules.user.api;

public record UserPostRequest (
        String username,
        String email,
        Role role
    ) {
}
