package com.example.practice_project.mapper;

import com.example.practice_project.domain.dto.AnimalDTO;
import com.example.practice_project.domain.dto.AnimalDetailDTO;
import com.example.practice_project.domain.dto.LoginDTO;
import com.example.practice_project.domain.vo.AnimalVO;
import com.example.practice_project.domain.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AnimalMapper {
    List<AnimalVO> selectTest();

    // 동물 테이블의 전체 목록을 불러오는 select
    List<AnimalDTO> selectAll();

    // 동물의 세부정보를 받아올 select
    AnimalDetailDTO selectOne(int id);

    // 동물 삭제하는 delete
    void deleteOne(int id);

    // 동물 목록을 추가하는 insert
    void insert(AnimalVO vo);

    // 동물 정보 수정하는 update
    void updateAnimal(AnimalVO vo);

    // 회원가입 insert
    void join(UserVO vo);

    // 로그인 select
    List<LoginDTO> login(String id);
}
