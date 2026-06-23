package org.scoula.board.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.scoula.board.domain.BoardVO;
import org.scoula.board.dto.BoardDTO;
import org.scoula.board.mapper.BoardMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Log4j2                      // 로깅
@Service                     // Service 계층 컴포넌트 + Bean 등록
@RequiredArgsConstructor     // final 필드 생성자 주입
public class BoardServiceImpl implements BoardService {
  private final BoardMapper boardMapper;  // Mapper 의존성 주입
  // getList()
  // get()


  /* *** CUD 메서드 - 처리된 객체를 반환하도록 변경 *** */

  // 목록 조회 서비스
  @Override
  public List<BoardDTO> getList() {
    log.info("getList..........");

    return boardMapper.getList().stream()    // List<BoardVO> → Stream<BoardVO>
            .map(BoardDTO::of)               // Stream<BoardVO> → Stream<BoardDTO>
            .toList();                       // Stream<BoardDTO> → List<BoardDTO>
  }

  // 단일 조회 서비스
  @Override
  public BoardDTO get(Long no) {
    log.info("get......" + no);

    BoardVO vo = boardMapper.get(no);               // DB에서 VO 조회
    BoardDTO dto = BoardDTO.of(vo);                 // VO → DTO 변환

    return Optional.ofNullable(dto)                 // null 안전성 처리
            .orElseThrow(NoSuchElementException::new);  // 없으면 예외 발생
  }

  /* create() 메서드 수정 */
  // 게시글 등록 서비스
  @Transactional   // 여러 DB 작업을 하나의 트랜잭션으로 처리
  @Override
  public BoardDTO create(BoardDTO board) {
    log.info("create......" + board);

    // 1. 게시글 등록
    BoardVO vo = board.toVo();         // DTO → VO 변환
    boardMapper.create(vo);            // DB에 저장


    // 생성된 게시글의 전체 정보를 반환
    return get(vo.getNo());
  }

  // 게시글 수정 서비스
  @Override
  public BoardDTO update(BoardDTO board) {
    log.info("update......" + board);

    boardMapper.update(board.toVo());  // 게시글 수정 수행
    // 수정된 게시글 정보를 반환
    return get(board.getNo());
  }

  // 게시글 삭제 서비스
  @Override
  public BoardDTO delete(Long no) {
    log.info("delete...." + no);

    // 삭제 전에 게시글 정보를 미리 조회
    BoardDTO board = get(no);

    // 실제 삭제 수행
    boardMapper.delete(no);

    // 삭제된 게시글 정보를 반환
    return board;
  }

  /* *** CUD 메서드 - 처리된 객체를 반환하도록 변경 *** */



  // getAttachment()
  // deleteAttachment()
  // upload()
}
