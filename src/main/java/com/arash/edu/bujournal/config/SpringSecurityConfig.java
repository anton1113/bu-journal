package com.arash.edu.bujournal.config;

import com.arash.edu.bujournal.service.auth.BuUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SpringSecurityConfig {

    private final BuUserService buUserService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.userDetailsService(buUserService)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(
                                "/",
                                "/index",
                                "/images/**",
                                "/styles/**",
                                "/js/**",
                                "/rest/login",
                                "/v3/api-docs/**",
                                "/swagger-ui/**")
                        .permitAll()
                        .anyRequest()
                        .authenticated())
                .formLogin(form -> form
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/me", true)
                .permitAll())
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login")
                        .permitAll())
                .csrf(csrf -> csrf.disable())
                .cors(cors -> {
                });
        return http.build();
    }
}
