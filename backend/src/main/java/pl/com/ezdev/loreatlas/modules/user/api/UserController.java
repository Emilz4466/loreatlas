package pl.com.ezdev.loreatlas.modules.user.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.com.ezdev.loreatlas.core.base.CrudControllerImpl;
import pl.com.ezdev.loreatlas.modules.user.domain.Role;
import pl.com.ezdev.loreatlas.modules.user.domain.User;
import pl.com.ezdev.loreatlas.modules.user.api.request.ChangePasswordRequest;
import pl.com.ezdev.loreatlas.modules.user.api.request.UserFindAllRequest;
import pl.com.ezdev.loreatlas.modules.user.api.request.UserPostRequest;
import pl.com.ezdev.loreatlas.modules.user.api.response.UserResponse;
import pl.com.ezdev.loreatlas.modules.user.service.UserService;

import java.security.Principal;

@RestController
@RequestMapping("/justpray/api/v1/users")
public class UserController
        extends CrudControllerImpl<User, Long, UserPostRequest, UserResponse, UserFindAllRequest> {

    private final UserService userService;

    public UserController(UserService userService) {
        super(userService);
        this.userService = userService;
    }

    @GetMapping("/by-email/{email}")
    public ResponseEntity<UserResponse> getUserByEmail(@PathVariable String email) {
        return ResponseEntity.ok(userService.getUserByEmail(email));
    }

    @GetMapping("/by-username/{username}")
    public ResponseEntity<UserResponse> getUserByUsername(@PathVariable String username) {
        return ResponseEntity.ok(userService.getUserByUsername(username));
    }

    @PostMapping("/{id}/change-role/{role}")
    public ResponseEntity<Void> changeRole(@PathVariable Long id, @PathVariable Role role) {
        userService.changeRole(id, role);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/change-password")
    public ResponseEntity<Void> changePassword(
            @RequestBody ChangePasswordRequest request,
            Principal connectedUser
    ) {
        userService.changePassword(request, connectedUser);
        return ResponseEntity.ok().build();
    }
}
