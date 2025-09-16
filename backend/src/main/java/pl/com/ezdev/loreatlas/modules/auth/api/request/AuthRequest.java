package pl.com.ezdev.loreatlas.modules.auth.api.request;

public record AuthRequest(
        String username,
        String password
) {
}
