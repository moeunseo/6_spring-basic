package com.example.practice_project.service;

import com.example.practice_project.domain.dto.AnimalDTO;
import com.example.practice_project.domain.dto.AnimalDetailDTO;
import com.example.practice_project.domain.dto.LoginDTO;
import com.example.practice_project.domain.vo.AnimalVO;
import com.example.practice_project.domain.vo.UserVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AnimalService {
    // mapper에서 받아온 sql을 실행시킬 비지니스 로직 메소드 선언

    // 동물 테이블의 전체 목록을 불러오는 select
    List<AnimalDTO> findAll();

    // 동물의 세부정보를 받아올 select
    AnimalDetailDTO findById(int id);

    // 동물을 삭제하는 sql
    void deleteById(int id);

    // 동물 목록을 추가하는 insert
    void save(AnimalVO vo);

    // 동물 정보 수정하는 update
    void edit(AnimalVO vo);

    // 회원가입 insert
    void userSave(UserVO vo);

    // 로그인 select
    List<LoginDTO> findUser(String id);
}
