package com.example.b_dependency.qualifier;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

// 스프링 컨테이너로 등록!
@Component
@Slf4j //lombok 안에 있는 어노테이션
public class StarCraft implements Game{

    @Override
    public void play() {
        log.info("Playing StarCraft");
    }
}
