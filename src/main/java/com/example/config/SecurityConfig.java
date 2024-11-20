package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/register", "/login","/adminlogin").permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable(); // 如果是前后端分离项目，禁用CSRF

        return http.build();
    }
}
