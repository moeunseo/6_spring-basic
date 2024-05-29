package com.example.b_dependency.qualifier;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Slf4j
// 구현체 중에서 이 클래스를 우선적으로 주입해라! 라는 어노테이션
@Primary
public class Lol implements Game{

    @Override
    public void play() {
        log.info("Playing Lol");
    }
}
