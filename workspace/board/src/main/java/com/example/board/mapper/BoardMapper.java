package com.example.board.mapper;

import com.example.board.domain.dto.BoardListDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BoardMapper {
    List<BoardListDTO> selectAll(@Param("startRow") int startRow, int endRow);

    int countBoard();
}
