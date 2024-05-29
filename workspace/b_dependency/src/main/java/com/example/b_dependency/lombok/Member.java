package com.example.b_dependency.lombok;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
public class Member{
    @NonNull
    private String name;
    private int age;
    private String address;
    private String gender;
}
