package org.scoula.board.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.scoula.board.domain.BoardVO;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardDTO {
    private Long no;
    private String title;
    private String content;
    private String writer;
    private Date regDate;
    private Date updateDate;

    //dto --> vo
    public BoardVO toVo(){
        return BoardVO.builder()
                .no(no)
                .title(title)
                .content(content)
                .writer(writer)
                .regDate(regDate)
                .updateDate(updateDate)
                .build();
    }

    //vo --> dto
    public static BoardDTO of(BoardVO vo) {

        //BoardDTO boardDTO = new BoardDTO();
        //vo에 있는 것을 꺼내서 dto에 넣어야함.
       return vo == null ? null : BoardDTO.builder()
               .no(vo.getNo())
               .title(vo.getTitle())
               .content(vo.getContent())
               .writer(vo.getWriter())
               .regDate(vo.getRegDate())
               .updateDate(vo.getUpdateDate())
               .build();
    }
}
