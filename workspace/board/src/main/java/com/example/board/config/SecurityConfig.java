package com.example.board.config;

import com.example.board.domain.dto.UsersDTO;
import com.example.board.domain.oauth.CustomOAuth2User;
import com.example.board.mapper.UsersMapper;
import com.example.board.service.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity // 웹 보안 활성화, Spring Security
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomOAuth2UserService customOAuth2UserService;
    private final UsersMapper usersMapper;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                // cross-site request forgery
                // csrf 보호 기능을 비활성화
                // post 방식으로 delete할 때 적어줘야함
                .csrf(AbstractHttpConfigurer::disable)
                // 요청에 대한 인증 및 인가를 설정
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll()
                )
                // 로그인을 OAuth2기반으로 구성!
                .oauth2Login(login -> login
                        .userInfoEndpoint(userInfo -> userInfo
                                .userService(customOAuth2UserService)
                        ).successHandler(authenticationSuccessHandler())
                )
                // 로그아웃
                .logout(logout -> logout
                        .logoutSuccessHandler((request, response, authentication) -> {
                            String clientId = "90e1de5b844be94081ddac49ac0ad036";
                            String logoutRedirectUri = "http://localhost:8090";
                            String logoutUri = "https://kauth.kakao.com/oauth/logout?client_id=" + clientId + "&logout_redirect_uri=" + logoutRedirectUri;
                            response.sendRedirect(logoutUri);
                        })
                )
                .build();
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return (request, response, auth) -> {
            CustomOAuth2User customOAuth2User = (CustomOAuth2User)auth.getPrincipal();
            UsersDTO user = usersMapper.findByProviderId(customOAuth2User.getProviderId());

            if(user.getRole().equals("new")){
                response.sendRedirect("/board/join");
            }
            else {
                // 로그인이 성공하면 메인화면으로 /board/list 로 페이지 이동
                response.sendRedirect("/board/list");
            }
        };
    }
}
