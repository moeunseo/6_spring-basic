package com.example.practice_project.service;

import com.example.practice_project.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
// 로그인을 하기 위해 필요한 서비스
public class UserDetailService implements UserDetailsService {

    private final UserMapper userMapper;

    // UserDetails = UserVO
    // 로그인 버튼을 누르면 아이디와 비밀번호가 자동으로 넘어와 메소드가 실행이 된다.
    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        return userMapper.selectOne(userId);
    }
}
