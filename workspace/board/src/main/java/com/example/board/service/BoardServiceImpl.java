package com.example.board.service;

import com.example.board.domain.dto.BoardDTO;
import com.example.board.domain.dto.BoardListDTO;
import com.example.board.domain.dto.FileDTO;
import com.example.board.domain.vo.FileVO;
import com.example.board.mapper.BoardMapper;
import com.example.board.mapper.FileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardMapper boardMapper;
    private final FileMapper fileMapper;

    @Override
    public List<BoardListDTO> selectAll(int pageNo, int pageSize) {
        int startRow = (pageNo - 1) * pageSize;
        int endRow = pageNo * pageSize;

        return boardMapper.selectAll(startRow, endRow);
    }

    @Override
    public int countBoard() {
        return boardMapper.countBoard();
    }

    @Override
    @Transactional // 해당 메소드를 하나의 트랜잭션으로 묶는다.
    public void saveBoard(BoardDTO board, List<MultipartFile> files) {
        // 다음 시퀀스 번호를 받아와서
        Long boardId = boardMapper.getSeq();
        // insert 할 시퀀스 번호 먼저 지정
        board.setBoardId(boardId);
        // 후에 게시글 작성하게 하기
        boardMapper.saveBoard(board); // 게시글 정보 저장

        // 현재 날짜를 기반으로 폴더 경로 생성
        LocalDate now = LocalDate.now(); // 년.월.일
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String datePath = now.format(formatter);

        for (MultipartFile file : files) {
            // 방어코드 (있어도 되고, 없어도 되지만 써주기)
            if (file.isEmpty()) continue; // 파일이 비어있으면 건너뜀

            // 내가 정해줬던 파일 이름
            String originalFileName = file.getOriginalFilename();
            // 중복되지 않은 값을 만드는 패턴
            // 다운로드 할 파일 이름
            String storedFileName = UUID.randomUUID().toString().replaceAll("-", "") + "_" + originalFileName;
            Long fileSize = file.getSize();

            try {
                // 파일 저장 경로 설정
                Path directoryPath = Paths.get("C:/upload/" + datePath);
                if (!Files.exists(directoryPath)) {
                    Files.createDirectories(directoryPath); // 폴더가 없으면 생성
                }
                Path filePath = directoryPath.resolve(storedFileName);
                // 파일 저장
                Files.copy(file.getInputStream(), filePath);

                FileDTO fileDTO = new FileDTO();
                fileDTO.setBoardId(boardId);
                fileDTO.setOriginalFileName(originalFileName);
                fileDTO.setStoredFileName(directoryPath + "/" + storedFileName);
                fileDTO.setFileSize(fileSize);

                fileMapper.insertFile(FileVO.toEntity(fileDTO)); // 파일 정보 저장

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}