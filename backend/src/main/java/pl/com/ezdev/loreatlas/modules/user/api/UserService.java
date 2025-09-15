package pl.com.ezdev.loreatlas.modules.user.api;

import pl.com.ezdev.loreatlas.cmm.CrudService;
import pl.com.ezdev.loreatlas.modules.user.User;

import java.security.Principal;

public interface UserService
        extends CrudService<User, Long, UserPostRequest, UserResponse, UserFindAllRequest> {

    UserResponse getUserByEmail(String email);

    UserResponse getUserByUsername(String username);

    void changeRole(Long id, Role role);

    void changePassword(ChangePasswordRequest request, Principal connectedUser);
}