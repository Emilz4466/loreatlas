package pl.com.ezdev.loreatlas.modules.user.api;

import lombok.Builder;

@Builder
public record ChangePasswordRequest(
        String currentPassword,
        String newPassword,
        String confirmationPassword
) {
}
