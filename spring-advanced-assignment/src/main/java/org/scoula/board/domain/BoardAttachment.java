package org.scoula.board.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardAttachment {
    private Long id;
    private Long boardId;
    private String originalName;
    private String contentType;
    private Long fileSize;
    private byte[] fileData;
}
