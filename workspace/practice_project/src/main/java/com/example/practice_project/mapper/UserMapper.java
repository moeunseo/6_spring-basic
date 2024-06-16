package com.example.practice_project.mapper;

import com.example.practice_project.domain.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    // 회원가입할 sql
    void insert(UserVO vo);

    // 로그인할 sql
    UserVO selectOne(String userId);

}
