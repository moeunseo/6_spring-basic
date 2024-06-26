package com.example.board.service;

import com.example.board.domain.dto.CommentListDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentService {
    // 해당 게시글의 댓글 목록 보기
    List<CommentListDTO> getCommentById(Long boardId);
}
