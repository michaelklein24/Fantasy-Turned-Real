package com.company.fantasyturnedreal.config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;

//@Configuration
//@EnableWebSecurity
public class SecurityConfig {
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        SecurityContext context = SecurityContextHolder.createEmptyContext();
//        Authentication authentication =
//                new TestingAuthenticationToken("username", "password", "ROLE_USER");
//        context.setAuthentication(authentication);
//
//        SecurityContextHolder.setContext(context);
//        http
//                .csrf(Customizer.withDefaults())
//                .authorizeHttpRequests(authorize -> authorize
//                        .requestMatchers(PathRequest.toH2Console()).permitAll()
//                        .anyRequest().permitAll())
//                .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable()))
//                .httpBasic(Customizer.withDefaults())
//                .formLogin(Customizer.withDefaults())
//                .logout(Customizer.withDefaults());
//        return http.build();
//    }
}
