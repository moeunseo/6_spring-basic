package com.example.practice_project.service;

import com.example.practice_project.domain.dto.AnimalDTO;
import com.example.practice_project.domain.dto.AnimalDetailDTO;
import com.example.practice_project.domain.vo.AnimalVO;
import com.example.practice_project.mapper.AnimalMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimalServiceImpl implements AnimalService {

    // 생성자 주입!
    private final AnimalMapper animalMapper;

    // 동물 테이블의 전체 목록을 불러오는 select
    @Override
    public List<AnimalDTO> findAll() {
        return animalMapper.selectAll();
    }

    // 동물의 세부정보를 받아올 select
    @Override
    public AnimalDetailDTO findById(int id) {
        return animalMapper.selectOne(id);
    }

    // 현재 세부정보를 보고 있는 동물 delete
    @Override
    public void deleteById(int id) {
        animalMapper.deleteOne(id);
    }

    // 동물 목록을 추가하는 insert
    @Override
    public void save(AnimalVO vo) {
        animalMapper.insert(vo);
    }

    // 동물 정보 수정하는 update
    @Override
    public void edit(AnimalVO vo) {
        animalMapper.updateAnimal(vo);
    }
}
