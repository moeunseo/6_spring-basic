package com.example.practice_project.domain.vo;

import com.example.practice_project.domain.dto.AnimalInsertDTO;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@ToString
@NoArgsConstructor
public class AnimalVO {
    private int id;
    private String name;
    private String species;
    private String description;

    @Builder
    public AnimalVO(int id, String name, String species, String description) {
        this.id = id;
        this.name = name;
        this.species = species;
        this.description = description;
    }

    // 동물의 정보 삽입 시 DTO로 받아와 VO로 변경하는 메소드
    // VO에는 setter가 없기에..
    public static AnimalVO toEntity(AnimalInsertDTO animalInsertDTO) {
        return AnimalVO.builder()
                .id(animalInsertDTO.getId())
                .name(animalInsertDTO.getName())
                .species(animalInsertDTO.getSpecies())
                .description(animalInsertDTO.getDescription()).build();
    }
}
