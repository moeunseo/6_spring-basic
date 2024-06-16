package com.example.practice_project.service;

import com.example.practice_project.domain.dto.UserDTO;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    // 회원가입할 insert
    void save(UserDTO dto);

}
