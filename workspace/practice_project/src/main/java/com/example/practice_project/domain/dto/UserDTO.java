package com.example.practice_project.domain.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class UserDTO {
    private Long id;
    private String userId;
    private String email;
    private String password;
    private String name;
}
