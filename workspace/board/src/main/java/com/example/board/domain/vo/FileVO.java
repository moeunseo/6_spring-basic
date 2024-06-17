package com.example.board.domain.vo;

import com.example.board.domain.dto.FileDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Getter
@ToString
@NoArgsConstructor
public class FileVO {

    private Long fileId;
    // 실제 파일명
    // 게시판 상세보기 페이지에서 실제 노출할 이름
    private String originalFileName;
    // 경로, 우리가 다운로드를 진행할 때 사용
    private String storedFileName;
    private Long fileSize;
    private LocalDateTime uploadTime;
    // FK, 어느 게시글에 첨부파일이야?
    private Long boardId;

    @Builder
    public FileVO (Long fileId, String originalFileName, String storedFileName, Long fileSize, LocalDateTime uploadTime, Long boardId) {
        this.fileId = fileId;
        this.originalFileName = originalFileName;
        this.storedFileName = storedFileName;
        this.fileSize = fileSize;
        this.uploadTime = uploadTime;
        this.boardId = boardId;
    }

    public static FileVO toEntity(FileDTO dto) {
        return FileVO.builder()
                .fileId(dto.getFileId())
                .originalFileName(dto.getOriginalFileName())
                .storedFileName(dto.getStoredFileName())
                .fileSize(dto.getFileSize())
                .uploadTime(dto.getUploadTime())
                .boardId(dto.getBoardId())
                .build();
    }
}
