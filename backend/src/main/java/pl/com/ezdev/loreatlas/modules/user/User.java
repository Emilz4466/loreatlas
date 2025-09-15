package pl.com.ezdev.loreatlas.modules.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import pl.com.ezdev.loreatlas.cmm.BaseEntity;
import pl.com.ezdev.loreatlas.modules.user.api.Role;

import java.util.List;

@Table(name = "app_user")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@jakarta.persistence.Entity
public class User extends BaseEntity {

    @Column(nullable = false)
    private String username;

    @Column(unique = true, length = 60, nullable = false)
    @Email(message = "{errors.invalid_email}")
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @OneToMany
    @JoinTable(
            name = "user_tokens",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "token_id")
    )
    private List<Token> tokens;
}