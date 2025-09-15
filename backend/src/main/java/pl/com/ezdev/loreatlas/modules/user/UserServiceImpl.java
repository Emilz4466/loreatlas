package pl.com.ezdev.loreatlas.modules.user;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.com.ezdev.loreatlas.cmm.CrudServiceImpl;
import pl.com.ezdev.loreatlas.cmm.SpecificationBuilder;
import pl.com.ezdev.loreatlas.cmm.exception.EntityNotFoundException;
import pl.com.ezdev.loreatlas.modules.user.api.*;
import pl.com.ezdev.loreatlas.modules.user.api.mapper.UserPostRequestMapper;
import pl.com.ezdev.loreatlas.modules.user.api.mapper.UserResponseMapper;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class UserServiceImpl
        extends CrudServiceImpl<User, Long, UserPostRequest, UserResponse, UserFindAllRequest>
        implements UserService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    protected JpaRepository<User, Long> getRepository() {
        return userRepo;
    }

    @Override
    protected User mapRequestToEntity(UserPostRequest request) {
        User user = UserPostRequestMapper.INSTANCE.requestToEntity(request);
        user.setRole(Role.USER);
        return user;
    }

    @Override
    protected void updateEntityFromRequest(UserPostRequest request, User entity) {
        UserPostRequestMapper.INSTANCE.updateEntityFromRequest(request, entity);
    }

    @Override
    protected UserResponse mapEntityToResponse(User entity) {
        return UserResponseMapper.INSTANCE.entityToResponse(entity);
    }

    @Override
    protected Specification<User> buildSpecification(UserFindAllRequest request) {
        return new SpecificationBuilder<User>()
                .addFilter("username", request.username())
                .addFilter("email", request.email())
                .addFilter("role", request.role() != null ? request.role().name() : null)
                .build();
    }

    // --- metody specyficzne dla domeny ---
    @Override
    public UserResponse getUserByEmail(String email) {
        return userRepo.findByEmail(email)
                .map(UserResponseMapper.INSTANCE::entityToResponse)
                .orElseThrow(() -> new EntityNotFoundException("User not found with email = " + email));
    }

    @Override
    public UserResponse getUserByUsername(String username) {
        return userRepo.findByUsername(username)
                .map(UserResponseMapper.INSTANCE::entityToResponse)
                .orElseThrow(() -> new EntityNotFoundException("User not found with username = " + username));
    }

    @Override
    public void changeRole(Long id, Role role) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id = " + id));
        user.setRole(role);
        userRepo.save(user);
    }

    @Override
    public void changePassword(ChangePasswordRequest request, Principal connectedUser) {
        User user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();

        if (!passwordEncoder.matches(request.currentPassword(), user.getPassword())) {
            throw new IllegalStateException("Wrong password");
        }
        if (!request.newPassword().equals(request.confirmationPassword())) {
            throw new IllegalStateException("Passwords are not the same");
        }

        user.setPassword(passwordEncoder.encode(request.newPassword()));
        userRepo.save(user);
    }
}


