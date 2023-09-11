package com.arash.edu.bujournal.service.auth;

import com.arash.edu.bujournal.domain.BuUser;
import com.arash.edu.bujournal.error.NotFoundException;
import com.arash.edu.bujournal.repository.BuUserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class BuUserService implements UserDetailsService {

    private final BuUserRepository buUserRepository;

    public String findUsernameByNullableId(UUID id) {
        log.info("Get user by nullable id [{}]", id);
        if (id == null) {
            return null;
        }
        return buUserRepository.findById(id)
                .map(BuUser::getUsername)
                .orElseThrow(() -> new NotFoundException("User not found by id " + id));
    }

    public BuUser deleteUserByExternalId(@NonNull UUID externalId) {
        log.info("Delete user by externalId {}", externalId);
        return buUserRepository.deleteByExternalId(externalId);
    }

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
