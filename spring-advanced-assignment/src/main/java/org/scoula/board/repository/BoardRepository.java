package org.scoula.board.repository;

import org.scoula.board.domain.Board;
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
public class BoardRepository {
    private static final RowMapper<Board> ROW_MAPPER = (rs, rowNum) -> Board.builder()
            .id(rs.getLong("id"))
            .title(rs.getString("title"))
            .content(rs.getString("content"))
            .writer(rs.getString("writer"))
            .createdAt(rs.getTimestamp("created_at").toLocalDateTime())
            .build();

    private final JdbcTemplate jdbcTemplate;

    public BoardRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Long save(Board board) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO board(title, content, writer) VALUES (?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, board.getTitle());
            statement.setString(2, board.getContent());
            statement.setString(3, board.getWriter());
            return statement;
        }, keyHolder);
        return keyHolder.getKey().longValue();
    }

    public List<Board> findAll() {
        return jdbcTemplate.query(
                "SELECT id, title, content, writer, created_at FROM board ORDER BY id DESC",
                ROW_MAPPER);
    }

    public Optional<Board> findById(Long id) {
        return jdbcTemplate.query(
                        "SELECT id, title, content, writer, created_at FROM board WHERE id = ?",
                        ROW_MAPPER,
                        id)
                .stream()
                .findFirst();
    }
}
