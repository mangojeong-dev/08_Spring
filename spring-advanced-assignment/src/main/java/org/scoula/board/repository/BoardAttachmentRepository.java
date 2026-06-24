package org.scoula.board.repository;

import org.scoula.board.domain.BoardAttachment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Repository
public class BoardAttachmentRepository {
    private static final RowMapper<BoardAttachment> METADATA_ROW_MAPPER =
            (rs, rowNum) -> BoardAttachment.builder()
                    .id(rs.getLong("id"))
                    .boardId(rs.getLong("board_id"))
                    .originalName(rs.getString("original_name"))
                    .contentType(rs.getString("content_type"))
                    .fileSize(rs.getLong("file_size"))
                    .build();

    private final JdbcTemplate jdbcTemplate;

    public BoardAttachmentRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Long save(BoardAttachment attachment) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO board_attachment" +
                            "(board_id, original_name, content_type, file_size, file_data) " +
                            "VALUES (?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, attachment.getBoardId());
            statement.setString(2, attachment.getOriginalName());
            statement.setString(3, attachment.getContentType());
            statement.setLong(4, attachment.getFileSize());
            statement.setBytes(5, attachment.getFileData());
            return statement;
        }, keyHolder);
        return keyHolder.getKey().longValue();
    }

    public List<BoardAttachment> findMetadataByBoardId(Long boardId) {
        return jdbcTemplate.query(
                "SELECT id, board_id, original_name, content_type, file_size " +
                        "FROM board_attachment WHERE board_id = ? ORDER BY id",
                METADATA_ROW_MAPPER,
                boardId);
    }

    public Optional<BoardAttachment> findById(Long id) {
        return jdbcTemplate.query(
                        "SELECT id, board_id, original_name, content_type, file_size, file_data " +
                                "FROM board_attachment WHERE id = ?",
                        (rs, rowNum) -> BoardAttachment.builder()
                                .id(rs.getLong("id"))
                                .boardId(rs.getLong("board_id"))
                                .originalName(rs.getString("original_name"))
                                .contentType(rs.getString("content_type"))
                                .fileSize(rs.getLong("file_size"))
                                .fileData(rs.getBytes("file_data"))
                                .build(),
                        id)
                .stream()
                .findFirst();
    }
}
