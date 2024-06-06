package com.example.practice_project.mapper;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class AnimalMapperTest {

    @Autowired
    private AnimalMapper animalMapper;

    @Test
    void test(){
        log.info(animalMapper.selectTest().toString());
    }
}