package com.example.board.mapper;

import com.example.board.domain.dto.FileDTO;
import com.example.board.domain.vo.FileVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FileMapper {

    // 첨부파일 insert
    // 첨부파일은 게시글이 insert 될 때 날아가면 된다.
    void insertFile(FileVO vo);

    // 상세보기 페이지 들어갈 때, 첨부파일 다운로드
    List<FileDTO> selectFileList(Long boardId);

    // 첨부파일 db 에서 삭제
    // 게시글 업데이트할 때 사용할 예정
    void deleteFile(Long boardId);

}
