package com.example.practice_project.mapper;

import com.example.practice_project.domain.dto.AnimalDTO;
import com.example.practice_project.domain.dto.AnimalDetailDTO;
import com.example.practice_project.domain.vo.AnimalVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AnimalMapper {

    // 동물 테이블의 전체 목록을 페이징 하여 가져오는 select
    List<AnimalDTO> selectAll(@Param("startRow") int startRow, int endRow);

    // 데이터의 총 개수 select
    int countAnimals();

    // 동물의 세부정보를 받아올 select
    AnimalDetailDTO selectOne(int id);

    // 동물 삭제하는 delete
    void deleteOne(int id);

    // 동물 목록을 추가하는 insert
    void insert(AnimalVO vo);

    // 동물 정보 수정하는 update
    void updateAnimal(AnimalVO vo);
}
