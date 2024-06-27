package com.example.board.service;

import com.example.board.domain.dto.CommentDTO;
import com.example.board.domain.dto.CommentListDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentService {
    // 해당 게시글의 댓글 목록 보기
    List<CommentListDTO> getCommentById(Long boardId);

    // 댓글 추가
    void saveComment(CommentDTO commentDTO);

    // 댓글 삭제
    void deleteComment(Long commentId);

    // 댓글 수정
    void updateComment(CommentDTO commentDTO);
}
