package org.scoula.board.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.scoula.board.domain.BoardAttachment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BoardAttachmentRepositoryTest {
    private BoardAttachmentRepository repository;

    @BeforeEach
    void setUp() {
        DataSource dataSource = new DriverManagerDataSource(
                "jdbc:h2:mem:attachment;MODE=MySQL;DB_CLOSE_DELAY=-1",
                "sa",
                "");
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.execute("DROP TABLE IF EXISTS board_attachment");
        jdbcTemplate.execute("""
                CREATE TABLE board_attachment (
                    id BIGINT AUTO_INCREMENT PRIMARY KEY,
                    board_id BIGINT NOT NULL,
                    original_name VARCHAR(255),
                    content_type VARCHAR(100),
                    file_size BIGINT,
                    file_data LONGBLOB
                )
                """);
        repository = new BoardAttachmentRepository(jdbcTemplate);
    }

    @Test
    void storesAndReadsBlobWithoutUsingDisk() {
        byte[] data = "blob-content".getBytes(StandardCharsets.UTF_8);
        Long id = repository.save(BoardAttachment.builder()
                .boardId(1L)
                .originalName("sample.txt")
                .contentType("text/plain")
                .fileSize((long) data.length)
                .fileData(data)
                .build());

        BoardAttachment saved = repository.findById(id).orElseThrow();
        assertEquals("sample.txt", saved.getOriginalName());
        assertEquals(data.length, saved.getFileSize());
        assertArrayEquals(data, saved.getFileData());
        assertTrue(repository.findMetadataByBoardId(1L).get(0).getFileData() == null);
    }
}
