package ua.hehmde.kvjewelry.util;

import org.springframework.security.core.context.SecurityContextHolder;
import ua.hehmde.kvjewelry.dto.UserDTO;

public class UserSession {
    public static UserDTO getCurrentUser() {
        return (UserDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public static String getCurrentUserRole() {
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities().toArray()[0].toString();
    }

    public static long getCurrentUserId() {
        return ((UserDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
    }
}
