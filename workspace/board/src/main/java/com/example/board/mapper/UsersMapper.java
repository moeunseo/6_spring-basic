package com.example.board.mapper;

import com.example.board.domain.dto.UsersDTO;
import com.example.board.domain.vo.UsersVO;
import org.apache.ibatis.annotations.Mapper;

// 서비스 구현은 CustomOAuth2UserService
@Mapper
public interface UsersMapper {

    // 회원가입이 되어있는 id를 찾는 select
    UsersDTO findByProviderId(String providerId);

    // 회원가입 insert
    void saveUser(UsersVO vo);

    // 회원의 정보나 로그인 시간을 수정하는 update
    void updateUser(UsersVO vo);
}
