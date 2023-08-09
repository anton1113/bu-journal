package com.arash.edu.bujournal.util;

import com.arash.edu.bujournal.domain.BuUser;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class BuSecurityUtil {

    public static Optional<BuUser> getLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return Optional.empty();
        }
        BuUser loggedInUser = (BuUser) authentication.getPrincipal();
        return Optional.of(loggedInUser);
    }
}
