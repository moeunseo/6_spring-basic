package com.example.board.service;

import com.example.board.domain.dto.FileDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FileService {

    // 파일 목록을 불러오는 select
    List<FileDTO> getFileListByBoardId(Long boardId);

    // 첨부파일 가져오기
    // 다운로드할 때 사용할 예정
    FileDTO getFileById(Long fileId);
}
