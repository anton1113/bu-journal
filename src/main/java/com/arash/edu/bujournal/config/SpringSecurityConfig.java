package com.arash.edu.bujournal.config;

import com.arash.edu.bujournal.service.auth.BuUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SpringSecurityConfig {

    private final BuUserService buUserService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.userDetailsService(buUserService)
                .authorizeRequests()
                .antMatchers("/", "/index", "/images/**", "/styles/**",
                        "/rest/login", "/v2/api-docs", "/api/swagger-ui/**", "/v3/api-docs/swagger-config")
                .permitAll()
            .and()
                .authorizeRequests()
                .anyRequest()
                .authenticated()
            .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/me", true)
                .permitAll()
            .and()
                .logout()
                .permitAll()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login")
            .and()
                .csrf()
                .disable()
                .cors();
        return http.build();
    }
}
