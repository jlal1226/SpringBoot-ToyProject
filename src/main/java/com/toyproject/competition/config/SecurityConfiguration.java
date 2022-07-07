package com.toyproject.competition.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/post/**").authenticated()
                .antMatchers("/comment/**").authenticated()
                .anyRequest().permitAll();
        http
                .formLogin()
                .loginPage("/loginForm")
                .loginProcessingUrl("/login") // login 주소가 호출되면 시큐리티가 대신 진행
                .defaultSuccessUrl("/")
                .failureUrl("/loginForm");

        http
                .logout()
                .logoutUrl("/logout")
                .deleteCookies("JSESSIONID", "remember-me")
                .logoutSuccessUrl("/");


        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
