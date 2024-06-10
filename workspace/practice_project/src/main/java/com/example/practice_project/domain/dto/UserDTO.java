package com.example.practice_project.domain.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class UserDTO {
    private int user_number;
    private String id;
    private int pwd;
    private String name;
}
