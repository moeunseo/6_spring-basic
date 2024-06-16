package com.example.practice_project.service;

import com.example.practice_project.domain.dto.UserDTO;
import com.example.practice_project.domain.vo.UserVO;
import com.example.practice_project.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    // 비밀번호를 암호화하는 것을 주입받기
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    // 회원가입 sql
    @Override
    public void save(UserDTO dto) {
        // 디비에 넣을 때 필수로 암호화하는 코드
        dto.setPassword(bCryptPasswordEncoder.encode(dto.getPassword()));
        UserVO vo = UserVO.toEntity(dto);

        userMapper.insert(vo);
    }
}
