package com.example.medicalregister.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // Authorization rules
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(new AntPathRequestMatcher("/h2-console/**")).permitAll()  // open H2 console
                        .requestMatchers(new AntPathRequestMatcher("/**/*.xhtml")).permitAll()  // allow access to all .xhtml pages
                        .requestMatchers(new AntPathRequestMatcher("/javax.faces.resource/**")).permitAll()  // allow access to JSF static resources
                        .anyRequest().authenticated()                   // auth required for rest
                )
                // OAuth2 login enabled
                .oauth2Login(Customizer.withDefaults())
                // CSRF ignoring H2 console endpoints and .xhtml pages
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers(new AntPathRequestMatcher("/h2-console/**"))
                        .ignoringRequestMatchers(new AntPathRequestMatcher("/**/*.xhtml"))
                        .ignoringRequestMatchers(new AntPathRequestMatcher("/javax.faces.resource/**"))
                )
                // Allow frames from same origin (needed for H2 console UI)
                .headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()));

        return http.build();
    }
}