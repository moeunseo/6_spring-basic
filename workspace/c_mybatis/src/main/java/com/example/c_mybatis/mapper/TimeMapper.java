package com.example.c_mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;

// resource 안에 있는 mapper 파일과 매핑시켜준다.
@Mapper
public interface TimeMapper {
    String getTime();
}
