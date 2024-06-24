package com.example.board.service;

import com.example.board.domain.dto.FileDTO;
import com.example.board.mapper.FileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final FileMapper fileMapper;

    // 파일 목록을 불러오는 select
    @Override
    public List<FileDTO> getFileListByBoardId(Long boardId) {
        return fileMapper.selectFileList(boardId);
    }

    // 첨부파일 가져오기
    // 다운로드할 때 사용할 예정
    @Override
    public FileDTO getFileById(Long fileId) {
        return fileMapper.getFileById(fileId);
    }
}
