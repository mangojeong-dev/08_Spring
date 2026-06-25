package org.scoula.board.controller;

import lombok.extern.slf4j.Slf4j;
import org.scoula.board.dto.BoardDTO;
import org.scoula.board.service.BoardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j                           // 로깅을 위한 Lombok 어노테이션
// ---
// 요청, 응답 처리(단, 모든 요청과 응답은 "비동기"이다)
@RestController                  // @Controller + @ResponseBody
@RequestMapping("/api/board")     // 기본 URL 패턴 설정
@RequiredArgsConstructor         // final 필드 생성자 자동 생성
public class BoardController {

  // 의존성 주입: BoardService를 통해 비즈니스 로직 처리
  final private BoardService service;

  /**
   * 전체 목록 조회
   * GET: http://localhost:8080/api/board
   * @return ResponseEntity
   *         - 200 OK: 목록 조회 성공, 게시글 리스트 반환 (빈 리스트 포함)
   *         - 204 No Content: 조회 성공했지만 게시글이 하나도 없음
   *         - 500 Internal Server Error: 서버 내부 오류 (DB 연결 실패 등)
   */
  @GetMapping("")
  public ResponseEntity<List<BoardDTO>> getList() {
    log.info("============> 게시글 전체 목록 조회");

    List<BoardDTO> list = service.getList();

    // ResponseEntity : HTTP 응답을 제어하기 위한 객체
    return ResponseEntity.ok(list); // 200 OK + 데이터 반환
    // +응답 헤더는 application/json (@RestController)
  }



  /**
   * 개별 게시글 조회
   * GET: http://localhost:8080/api/board/{no}
   * @param no 게시글 번호(PK)
   * @return ResponseEntity
   *         - 200 OK: 게시글 조회 성공, 게시글 정보 반환
   *         - 404 Not Found: 해당 번호의 게시글이 존재하지 않음
   *         - 400 Bad Request: 잘못된 게시글 번호 형식 (음수, 문자 등)
   *         - 500 Internal Server Error: 서버 내부 오류
   */

  // @PathVariable : URL 경로에 있는 값을 매개변수로 이용할 수 있게 하는 어노테이션
  @GetMapping("/{no:[0-9]+}") // <- 정규표현식 - 숫자만 매핑
  public ResponseEntity<BoardDTO> get(@PathVariable Long no) {
    log.info("============> 게시글 상세 조회: " + no);

    BoardDTO board = service.get(no);
    return ResponseEntity.ok(board);
  }


  /**
   * 새 게시글 작성
   * POST: http://localhost:8080/api/board
   * @param board 작성할 게시글 데이터 (제목, 내용, 작성자 필수)
   * @return ResponseEntity
   *         - 200 OK: 게시글 생성 성공 시 생성된 게시글 데이터 반환
   *         - 400 Bad Request: 잘못된 요청 데이터 (제목/내용 누락 등)
   *         - 500 Internal Server Error: 서버 내부 오류 발생 시
   */
  // @RequestBody : "단일 바디"를 통째로 DTO/객체로 변환
  // -> 대표 헤더에서 사용 : application/json, application/xml, text/plain
  // VS
  // @ModelAttribute : "폼 바인딩" 개념. 키-값 쌍으로 자바 DTO에 바인딩
  // -> 대표 헤더 : application/x-www-form-urlencoded, multipart/form-data
  // -> 값이 ModelAttribute에 세팅되려면 dto에 getter/setter 필수로 작성

  // Content-Type(전송 방식)에 따라서 Spring이 내부적으로 어떤 어노테이션으로 처리할 지 결정
  @PostMapping("")
  public ResponseEntity<BoardDTO> create(@RequestBody BoardDTO board) {
    log.info("============> 게시글 생성: " + board);
    // key값과 DTO의 필드명이 일치하는 경우 해당 객체에 값을 담아줌
    // 새 게시글 생성 후 결과 반환
    BoardDTO createdBoard = service.create(board);
    return ResponseEntity.ok(createdBoard);
  }


  /**
   * 게시글 수정
   * PUT: http://localhost:8080/api/board/{no}
   * @param no 수정할 게시글 번호(PK)
   * @param board 수정할 게시글 데이터 (제목, 내용 등)
   * @return ResponseEntity
   *         - 200 OK: 게시글 수정 성공 시 수정된 게시글 데이터 반환
   *         - 400 Bad Request: 잘못된 요청 데이터 (제목/내용 누락, 잘못된 번호 형식 등)
   *         - 404 Not Found: 수정할 게시글이 존재하지 않음
   *         - 500 Internal Server Error: 서버 내부 오류 발생 시
   */
  @PutMapping("/{no}")
  public ResponseEntity<BoardDTO> update(
          @PathVariable Long no,           // URL에서 게시글 번호 추출
          @RequestBody BoardDTO board      // 수정할 데이터 (JSON)
  ) {
    log.info("============> 게시글 수정: " + no + ", " + board);

    // 게시글 번호 설정 (안전성을 위해)
    board.setNo(no);
    BoardDTO updatedBoard = service.update(board);
    return ResponseEntity.ok(updatedBoard);
  }


  /**
   * 게시글 삭제
   * DELETE: http://localhost:8080/api/board/{no}
   * @param no 삭제할 게시글 번호(PK)
   * @return ResponseEntity
   *         - 200 OK: 게시글 삭제 성공 시 삭제된 게시글 데이터 반환
   *         - 204 No Content: 게시글 삭제 성공 (응답 데이터 불필요한 경우)
   *         - 400 Bad Request: 잘못된 게시글 번호 형식 (음수, 문자 등)
   *         - 404 Not Found: 삭제할 게시글이 존재하지 않음
   *         - 500 Internal Server Error: 서버 내부 오류 발생 시
   */
  @DeleteMapping("/{no}")
  public ResponseEntity<BoardDTO> delete(@PathVariable Long no) {
    log.info("============> 게시글 삭제: " + no);

    // 삭제된 게시글 정보를 반환
    BoardDTO deletedBoard = service.delete(no);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
//    return ResponseEntity.status(204).build();
//    return ResponseEntity.ok(deletedBoard);
  }
}