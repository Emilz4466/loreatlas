package pl.com.ezdev.loreatlas.modules.user.service;

import pl.com.ezdev.loreatlas.core.base.CrudService;
import pl.com.ezdev.loreatlas.modules.user.domain.User;
import pl.com.ezdev.loreatlas.modules.user.domain.Role;
import pl.com.ezdev.loreatlas.modules.user.api.request.ChangePasswordRequest;
import pl.com.ezdev.loreatlas.modules.user.api.request.UserFindAllRequest;
import pl.com.ezdev.loreatlas.modules.user.api.request.UserPostRequest;
import pl.com.ezdev.loreatlas.modules.user.api.response.UserResponse;

import java.security.Principal;

public interface UserService
        extends CrudService<User, Long, UserPostRequest, UserResponse, UserFindAllRequest> {

    UserResponse getUserByEmail(String email);

    UserResponse getUserByUsername(String username);

    void changeRole(Long id, Role role);

    void changePassword(ChangePasswordRequest request, Principal connectedUser);
}