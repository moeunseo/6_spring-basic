package com.example.board.controller;


import com.example.board.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentRestController {

    private final CommentService commentService;

    // 댓글 조회 (특정 게시글의 모든 댓글 조회하기)
    @GetMapping("/{boardId}")
    public ResponseEntity<?> getComments(@PathVariable Long boardId) {
        return ResponseEntity.ok(commentService.getCommentById(boardId));
    }
}
