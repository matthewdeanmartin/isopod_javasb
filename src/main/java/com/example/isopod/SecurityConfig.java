//package com.example.isopod;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
//import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.config.web.server.ServerHttpSecurity;
//import org.springframework.security.web.server.SecurityWebFilterChain;
//
//@Order(1)
//@Configuration
//@EnableWebFluxSecurity
//public class SecurityConfig
//{
//    @Bean
//    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
//        //.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//        return http.sessionManagement(x-> SessionCreationPolicy.STATELESS).sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//    }
//
//    @Bean
//    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
//        http
//                .sessionManagement((session) -> session. .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                );
//        return http.build();
//    }
//
//}