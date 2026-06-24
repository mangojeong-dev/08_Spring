package org.scoula.board.service;

import org.scoula.board.domain.Board;
import org.scoula.board.domain.BoardAttachment;
import org.scoula.board.repository.BoardAttachmentRepository;
import org.scoula.board.repository.BoardRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BoardServiceImpl implements BoardService {
    private final BoardRepository boardRepository;
    private final BoardAttachmentRepository attachmentRepository;

    public BoardServiceImpl(
            BoardRepository boardRepository,
            BoardAttachmentRepository attachmentRepository) {
        this.boardRepository = boardRepository;
        this.attachmentRepository = attachmentRepository;
    }

    @Override
    @Transactional
    public Long create(Board board, MultipartFile attachment) throws IOException {
        Long boardId = boardRepository.save(board);
        if (attachment != null && !attachment.isEmpty()) {
            attachmentRepository.save(BoardAttachment.builder()
                    .boardId(boardId)
                    .originalName(attachment.getOriginalFilename())
                    .contentType(attachment.getContentType())
                    .fileSize(attachment.getSize())
                    .fileData(attachment.getBytes())
                    .build());
        }
        return boardId;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Board> getList() {
        return boardRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Board get(Long id) {
        return boardRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("게시글을 찾을 수 없습니다: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<BoardAttachment> getAttachments(Long boardId) {
        return attachmentRepository.findMetadataByBoardId(boardId);
    }

    @Override
    @Transactional(readOnly = true)
    public BoardAttachment getAttachment(Long attachmentId) {
        return attachmentRepository.findById(attachmentId)
                .orElseThrow(() -> new NoSuchElementException(
                        "첨부파일을 찾을 수 없습니다: " + attachmentId));
    }
}
