package com.arash.edu.bujournal.rest;

import com.arash.edu.bujournal.api.LoginApi;
import com.arash.edu.bujournal.domain.BuUser;
import com.arash.edu.bujournal.dtos.BuUserDTO;
import com.arash.edu.bujournal.dtos.LoginRequest;
import com.arash.edu.bujournal.error.AuthenticationException;
import com.arash.edu.bujournal.mapper.BuUserMapper;
import com.arash.edu.bujournal.service.auth.BuUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class LoginRestController implements LoginApi {

    private final BuUserService buUserService;
    private final BuUserMapper buUserMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public BuUserDTO doLogin(LoginRequest loginRequest) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        BuUser user = buUserService.loadUserByUsername(username);
        checkPassword(password, user.getPassword());

        authenticate(user);

        return buUserMapper.toDTO(user);
    }

    private void checkPassword(String requestPassword, String encodedDbPassword) {
        if (!passwordEncoder.matches(requestPassword, encodedDbPassword)) {
            throw new AuthenticationException("Invalid password");
        }
    }

    private void authenticate(BuUser user) {
        Authentication auth = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);
    }
}
