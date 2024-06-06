package com.example.practice_project.domain.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class AnimalInsertDTO {
    private int id;
    private String name;
    private String species;
    private String description;
}
