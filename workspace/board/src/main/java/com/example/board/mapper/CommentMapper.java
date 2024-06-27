package com.example.board.mapper;

import com.example.board.domain.dto.CommentListDTO;
import com.example.board.domain.vo.CommentVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {
    // 해당 게시글의 댓글 목록 보기
    List<CommentListDTO> selectCommentById(Long boardId);

    // 댓글 추가
    void insertComment(CommentVO commentvo);

    // 댓글 삭제
    void deleteComment(Long commentId);

    // 댓글 수정
    void updateComment(CommentVO vo);
}
