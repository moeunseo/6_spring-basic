package com.example.practice_project.domain.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class AnimalDTO {
    private int id;
    private String name;
}
