package org.scoula.board.service;

import org.scoula.board.domain.Board;
import org.scoula.board.domain.BoardAttachment;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface BoardService {
    Long create(Board board, MultipartFile attachment) throws IOException;

    List<Board> getList();

    Board get(Long id);

    List<BoardAttachment> getAttachments(Long boardId);

    BoardAttachment getAttachment(Long attachmentId);
}
