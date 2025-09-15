package pl.com.ezdev.loreatlas.modules.user.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/justpray/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/getUser/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.get(id));
    }

    @PostMapping("/updateUser/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long id, @RequestBody UserPostRequest request) {
        return ResponseEntity.ok(userService.update(id, request));
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/findAllUsers")
    public List<UserResponse> findAllUsers(@RequestParam(required = false) String username, @RequestParam(required = false) String email, @RequestParam(required = false) Role role) {
        return userService.findAll(new UserFindAllRequest(username, email, role));
    }

    @GetMapping("/getUserByEmail/{email}")
    public ResponseEntity<UserResponse> getUserByEmail(@PathVariable String email) {
        return ResponseEntity.ok(userService.getUserByEmail(email));
    }

    @GetMapping("/getUserByEmail/{username}")
    public ResponseEntity<UserResponse> getUserByUsername(@PathVariable String username) {
        return ResponseEntity.ok(userService.getUserByUsername(username));
    }

    @PostMapping("/changeRole/{id}/{role}")
    public ResponseEntity<Void> changeRole(@PathVariable Long id, @PathVariable Role role) {
        userService.changeRole(id, role);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping
    public ResponseEntity<?> changePassword(
            @RequestBody ChangePasswordRequest request,
            Principal connectedUser
    ) {
        userService.changePassword(request, connectedUser);
        return ResponseEntity.ok().build();
    }
}
