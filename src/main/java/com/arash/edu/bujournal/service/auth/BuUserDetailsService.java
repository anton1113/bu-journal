package com.arash.edu.bujournal.service.auth;

import com.arash.edu.bujournal.domain.BuUser;
import com.arash.edu.bujournal.repository.BuUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class BuUserDetailsService implements UserDetailsService {

    private final BuUserRepository buUserRepository;

    @Override
    public BuUser loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Load user by username {}", username);
        BuUser buUser = buUserRepository.findByUsername(username);
        if (buUser == null) {
            throw new UsernameNotFoundException(username);
        }
        log.info("Loaded user {} by username {}", buUser, username);
        return buUser;
    }
}
