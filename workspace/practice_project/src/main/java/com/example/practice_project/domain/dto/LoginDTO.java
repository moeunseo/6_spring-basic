package com.example.practice_project.domain.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class LoginDTO {
    private String id;
    private int pwd;
}
