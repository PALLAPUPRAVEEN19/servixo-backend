package com.servixo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            // ✅ disable csrf (new syntax)
            .csrf(csrf -> csrf.disable())

            // ✅ authorization rules
            .authorizeHttpRequests(auth -> auth
                // public APIs
                .requestMatchers("/api/auth/**").permitAll()

                // TODO: re-enable when JWT is returned from login
                // temporarily permit all until JWT is wired into auth flow
                .anyRequest().permitAll()
            );

        return http.build();
    }
}