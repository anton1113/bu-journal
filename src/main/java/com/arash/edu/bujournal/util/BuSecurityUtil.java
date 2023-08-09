package com.arash.edu.bujournal.util;

import com.arash.edu.bujournal.domain.BuUser;
import com.arash.edu.bujournal.error.AuthenticationException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class BuSecurityUtil {

    public static BuUser getLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            throw new AuthenticationException("No logged in used found in security context");
        }
        return (BuUser) authentication.getPrincipal();
    }
}
