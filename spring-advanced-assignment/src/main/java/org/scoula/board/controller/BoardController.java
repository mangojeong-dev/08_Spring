package org.scoula.board.controller;

import org.scoula.board.domain.Board;
import org.scoula.board.domain.BoardAttachment;
import org.scoula.board.service.BoardService;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Controller
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("boards", boardService.getList());
        return "board/list";
    }

    @GetMapping("/create")
    public String createForm() {
        return "board/create";
    }

    @PostMapping("/create")
    public String create(
            Board board,
            @RequestParam(value = "attachment", required = false) MultipartFile attachment)
            throws IOException {
        Long boardId = boardService.create(board, attachment);
        return "redirect:/board/get?id=" + boardId;
    }

    @GetMapping("/get")
    public String get(@RequestParam Long id, Model model) {
        model.addAttribute("board", boardService.get(id));
        model.addAttribute("attachments", boardService.getAttachments(id));
        return "board/get";
    }

    @GetMapping("/attachments/download")
    public ResponseEntity<byte[]> download(@RequestParam Long id) {
        BoardAttachment attachment = boardService.getAttachment(id);
        MediaType mediaType = MediaType.APPLICATION_OCTET_STREAM;
        if (attachment.getContentType() != null && !attachment.getContentType().isBlank()) {
            try {
                mediaType = MediaType.parseMediaType(attachment.getContentType());
            } catch (IllegalArgumentException ignored) {
                // Use the safe binary default for malformed stored MIME types.
            }
        }

        ContentDisposition disposition = ContentDisposition.attachment()
                .filename(attachment.getOriginalName(), StandardCharsets.UTF_8)
                .build();

        return ResponseEntity.ok()
                .contentType(mediaType)
                .contentLength(attachment.getFileSize())
                .header(HttpHeaders.CONTENT_DISPOSITION, disposition.toString())
                .body(attachment.getFileData());
    }
}
