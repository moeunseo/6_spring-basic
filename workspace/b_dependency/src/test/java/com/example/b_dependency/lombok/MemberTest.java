package com.example.b_dependency.lombok;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class MemberTest {

    @Test
    void test(){
        Member member = new Member("모은서");
//        Member member1 = new Member("모은서");
        log.info(member.toString());
    }
}
