package com.bus.online.ticketmanagement.util;

import com.bus.online.ticketmanagement.model.entity.User;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class SecurityUtil {
    private SecurityUtil() {

    }

    public static Optional<User> getLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();

        if (authentication instanceof AnonymousAuthenticationToken) {
            return Optional.empty();
        }

        return Optional.ofNullable(authentication.getPrincipal())
                .filter(User.class::isInstance)
                .map(User.class::cast);
    }
}
