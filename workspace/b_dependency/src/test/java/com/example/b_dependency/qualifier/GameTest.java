package com.example.b_dependency.qualifier;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GameTest {

    @Autowired
    // @Qualifier 이거를 사용하지 않을 시, 어떤 클래스에서 play()메소드를 가져올지 몰라 오류 발생
//    @Qualifier("starCraft")
    private Game game;

    @Test
    void test(){
        game.play();
    }
}
