package ua.hehmde.kvjewelry.dto;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ua.hehmde.kvjewelry.entity.User;
import ua.hehmde.kvjewelry.entity.UserRole;
import ua.hehmde.kvjewelry.validation.ValidEmail;
import ua.hehmde.kvjewelry.validation.ValidLogin;
import ua.hehmde.kvjewelry.validation.ValidName;
import ua.hehmde.kvjewelry.validation.ValidPassword;

import java.util.Collection;
import java.util.Collections;

/**
 * Data transfer object for user info
 *
 * @author hehmde
 * @version 1.0
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO implements UserDetails {
    @ValidName
    private String firstName;

    @ValidName
    private String lastName;

    @ValidEmail
    private String email;

    @ValidLogin
    private String login;

    @ValidPassword
    private String password;

    private long id;

    private UserRole role;

    /**
     * Mapping constructor User -> UserDTO
     *
     * @param user
     */
    public UserDTO(User user) {
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.login = user.getLogin();
        this.role = user.getRole();
        this.id = user.getId();
        this.password = user.getPassword();
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", login='" + login + '\'' +
                '}';
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(role);
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
