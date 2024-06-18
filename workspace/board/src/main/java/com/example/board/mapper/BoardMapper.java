package com.example.board.mapper;

import com.example.board.domain.dto.BoardDTO;
import com.example.board.domain.dto.BoardDetailDTO;
import com.example.board.domain.dto.BoardListDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BoardMapper {
    // 게시판 목록
    List<BoardListDTO> selectAll(@Param("startRow") int startRow, int endRow);

    // 게시판 총 갯수
    // 페이징 처리 시 사용할 쿼리
    int countBoard();

    // 다음 시퀀스를 가져오기
    // 게시글 작성 때 사용할 쿼리
    Long getSeq();

    // 게시글 작성
    void saveBoard(BoardDTO board);

    // 게시글 상세보기
    BoardDetailDTO selectBoardDetail(Long boardId);

    // 조회수 + 1
    void plusView(Long boardId);
}

