package org.example.project0316.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {

        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        http
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/", "/login", "/loginProc","/join","/joinProc").permitAll()
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


        http
                .sessionManagement((auth) -> auth
                        .maximumSessions(1) // 하나의 id에서 로그인 할 수 있는 중복 로그인 허용 개수
                        .maxSessionsPreventsLogin(true)); // 1을 초과했을 경우 기존에 로그인 되어있는 것을 로그아웃 시킬지 아니면 새로 로그인하는 것을 막을지

        http
                .sessionManagement((auth) -> auth
                        .sessionFixation().changeSessionId());


        return http.build();
    }
}