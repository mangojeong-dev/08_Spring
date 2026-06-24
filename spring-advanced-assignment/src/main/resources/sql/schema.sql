CREATE DATABASE IF NOT EXISTS spring_advanced_assignment_db
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE spring_advanced_assignment_db;

CREATE TABLE IF NOT EXISTS board (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(200) NOT NULL,
    content TEXT,
    writer VARCHAR(50) NOT NULL,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS board_attachment (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    board_id BIGINT NOT NULL,
    original_name VARCHAR(255),
    content_type VARCHAR(100),
    file_size BIGINT,
    file_data LONGBLOB,
    CONSTRAINT fk_board_attachment_board
        FOREIGN KEY (board_id) REFERENCES board(id)
        ON DELETE CASCADE
);

CREATE INDEX idx_board_attachment_board_id
    ON board_attachment(board_id);
