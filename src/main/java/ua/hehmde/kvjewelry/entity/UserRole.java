package ua.hehmde.kvjewelry.entity;

import org.springframework.security.core.GrantedAuthority;

/**
 * User roles that affect user rights
 *
 * @author hehmde
 * @version 1.0
 */
public enum UserRole implements GrantedAuthority {
    USER,
    ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
