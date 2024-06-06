package com.example.practice_project.domain.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class AnimalDetailDTO {
    private int id;
    private String name;
    private String species;
    private String description;

    @Builder
    public AnimalDetailDTO(int id, String name, String species, String description) {
        this.id = id;
        this.name = name;
        this.species = species;
        this.description = description;
    }
}
