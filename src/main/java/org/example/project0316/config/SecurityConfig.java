package org.example.project0316.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        http
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/", "/login", "/loginProc").permitAll()
                        .requestMatchers("/admin").hasRole("ADMIN")
                        .requestMatchers("/my/**").hasAnyRole("ADMIN", "USER")
                        .anyRequest().authenticated()
                );


        http
                .formLogin((auth) -> auth.loginPage("/login") // admin 경로로 접근했을 때 자동으로 로그인페이지로 리다이렉션 해준다.
                        .loginProcessingUrl("/loginProc") // 로그인 요청을 보내면 시큐리티가 받아서 로그인 처리 진행해준다.
                        .permitAll() // 아무나 들어올 수 있게 해준다.
                );

        http
                .csrf((auth) -> auth.disable()); // 사이트 위변조 방지 설정 csrf가 동작되면 post요청을 보낼 때 csrf 토큰도 보내주어야 로그인이 진행된다.


        return http.build();
    }
}