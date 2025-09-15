package pl.com.ezdev.loreatlas.auth.api;

public record AuthRequest(
        String username,
        String password
) {
}
