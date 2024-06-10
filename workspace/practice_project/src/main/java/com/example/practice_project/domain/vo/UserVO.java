package com.example.practice_project.domain.vo;

import com.example.practice_project.domain.dto.UserDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Component
@Getter
@ToString
@NoArgsConstructor
public class UserVO {
    private int user_number;
    private String id;
    private int pwd;
    private String name;

    @Builder
    public UserVO(int user_number, String id, int pwd, String name) {
        this.user_number = user_number;
        this.id = id;
        this.pwd = pwd;
        this.name = name;
    }

    public static UserVO toEntity(UserDTO user) {
        return UserVO.builder()
                .user_number(user.getUser_number())
                .id(user.getId())
                .pwd(user.getPwd())
                .name(user.getName()).build();
    }
}
