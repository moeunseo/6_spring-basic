package com.example.practice_project.domain.vo;

import com.example.practice_project.domain.dto.UserDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
@Getter
@ToString
@NoArgsConstructor
// 스프링 시큐리티의 인증절차를 사용하기 위해 UserVO를 확장시킨다.
// 요청이 들어오면 서비스를 구현하기 위함
public class UserVO implements UserDetails {
    //pk
    private Long id;

    // 회원 id
    private String userId;

    // 이메일
    private String email;

    // 비밀번호
    // getter가 있고 이름이 동일하기 때문에 getPassword가 필요없다.
    private String password;

    // 이름도 넘어와서 로그인 성공 시 이름으로 보여줘보자
    private String name;

    @Builder
    public UserVO(Long id, String userId, String email, String password, String name) {
        this.id = id;
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public static UserVO toEntity(UserDTO user) {
        return UserVO.builder()
                .userId(user.getUserId())
                .email(user.getEmail())
                .password(user.getPassword())
                .name(user.getName())
                .build();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("basic"));
    }

    @Override
    public String getUsername() {
        return userId;
    }

    // 계정의 만료 여부
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 계정의 잠김 여부
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 자격 증명 만료 여부
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 계정의 활성화 여부
    @Override
    public boolean isEnabled() {
        return true;
    }
}
