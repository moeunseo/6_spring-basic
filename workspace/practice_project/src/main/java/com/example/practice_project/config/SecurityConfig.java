package com.example.practice_project.config;

import com.example.practice_project.service.UserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity // 웹 보안 활성화 > Spring Security를 사용하여
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserDetailService userDetailService;

    // configuration 어노테이션을 사용하면 Bean을 만들어야 한다.
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                // 요청에 대한 인증 및 인가를 설정
                .authorizeHttpRequests(auth -> auth
                        // 매개변수로 전달해주는 요청 주소가 있다면?
                        .requestMatchers(
                                // get은 생략 가능, post는 적어줘야 한다.
                                new AntPathRequestMatcher("/animal/list", "GET"),
                                new AntPathRequestMatcher("/animal"),
                                new AntPathRequestMatcher("/user/login"),
                                new AntPathRequestMatcher("/user/join")
                        ).permitAll() // 특정 요청에 대해서는 접근을 모두 허용!
                        // 로그인폼을 연결하지 않으면 권한이 없다는 페이지가 뜬다. (403에러)
                        .anyRequest().authenticated() // 나머지 요청들은 인증이 필요하도록 설정!
                )
                // 로그인을 폼 기반으로 구성
                .formLogin(form -> form
                        // 인증이 되지 않았다면 로그인 페이지를 띄어줄거야!
                                .loginPage("/user/login") // 사용할 로그인 페이지 요청 설정.
                        // spring security는 username을 pk로 생각
                        // username으로 쓸 매개변수 이름은 뭐야?
                        // 만약, username으로 동일하게 한다면 쓸 필요는 없다.
                                .usernameParameter("userId") // 사용자 ID 필드의 이름을 설정.
                        // 만약 pwd로 한다면 적어줘야함.
                        // password(스프링 시큐리티 디폴트)로 쓸 매개변수 이름은 뭐야?
//                        .passwordParameter("password") // 필드의 이름과 동일하다면 생략 가능!
//                        .defaultSuccessUrl("/product/list")
                                // 로그인 성공 시 이벤트 실행
                                .successHandler(authenticationSuccessHandler())

                )
                // 로그아웃
                .logout(logout -> logout
                        .logoutUrl("/user/logout")
                        .invalidateHttpSession(true) // 현재 세션 무효화 (세션을 비워준다.)
                )
                .build();
    }

    // 사용자가 로그인할 때 인증 요청을 처리하는 코드
    @Bean
    public AuthenticationManager authenticationManager(BCryptPasswordEncoder bCryptPasswordEncoder) throws Exception {
        // sql을 비교하여 실행
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        // 인증 절차를 실행할 때 userDetailsService를 실행할거야.
        daoAuthenticationProvider.setUserDetailsService(userDetailService);
        // 화면에서 입력받은 비밀번호를 암호화하여 실제 디비에 들어있는 암호화된 비밀번호와 비교
        // 회원가입 시 비밀번호는 암호화하여 저장
        daoAuthenticationProvider.setPasswordEncoder(bCryptPasswordEncoder);
        return new ProviderManager(daoAuthenticationProvider);
    }

    // 비밀번호를 암호화 해주는 메소드
    // 주입받지 않고 매개변수로 전달하여 사용
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 로그인이 성공하면 이동하는 핸들러
    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return (request, response, auth) -> {
            response.sendRedirect("/animal/list");
        };
    }
}