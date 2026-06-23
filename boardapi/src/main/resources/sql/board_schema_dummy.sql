-- MySQL 8.x
-- Run with an admin account if the database/user does not exist yet.

CREATE DATABASE IF NOT EXISTS scoula_db
    DEFAULT CHARACTER SET utf8mb4
    DEFAULT COLLATE utf8mb4_unicode_ci;

CREATE USER IF NOT EXISTS 'scoula'@'localhost' IDENTIFIED BY '1234';
GRANT ALL PRIVILEGES ON scoula_db.* TO 'scoula'@'localhost';
FLUSH PRIVILEGES;

USE scoula_db;

CREATE TABLE IF NOT EXISTS tbl_board (
    no BIGINT NOT NULL AUTO_INCREMENT,
    title VARCHAR(200) NOT NULL,
    content TEXT NOT NULL,
    writer VARCHAR(50) NOT NULL,
    reg_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (no),
    INDEX idx_tbl_board_reg_date (reg_date),
    INDEX idx_tbl_board_writer (writer)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO tbl_board (title, content, writer, reg_date, update_date) VALUES
('Spring MVC Board Project', 'Sample post for checking the Spring MVC and MyBatis board CRUD flow.', 'admin', '2026-06-01 09:10:00', '2026-06-01 09:10:00'),
('MyBatis Mapper Test Guide', 'Use BoardMapperTest to check list, get, and create mapper methods.', 'manager', '2026-06-02 10:20:00', '2026-06-02 10:20:00'),
('Create Page Check', 'Submit title, content, and writer from create.jsp to insert a new board row.', 'user1', '2026-06-03 11:30:00', '2026-06-03 11:30:00'),
('Detail Page Check', 'Use get.jsp to verify title, content, writer, reg_date, and update_date.', 'user2', '2026-06-04 12:40:00', '2026-06-04 12:40:00'),
('Update Feature Test', 'Change a post from update.jsp and confirm update_date is refreshed.', 'user3', '2026-06-05 13:50:00', '2026-06-05 14:10:00'),
('Delete Feature Test', 'Delete this row from the list or detail page to verify the delete mapper.', 'user4', '2026-06-06 15:00:00', '2026-06-06 15:00:00'),
('List Sort Check', 'The getList query in BoardMapper.xml sorts posts by no in descending order.', 'admin', '2026-06-07 16:10:00', '2026-06-07 16:10:00'),
('UTF8MB4 Storage Test', 'The table uses utf8mb4 so Korean or emoji content can be stored later.', 'tester', '2026-06-08 17:20:00', '2026-06-08 17:20:00'),
('Long Content Display Test', 'This row has longer text for checking whether the detail page displays content without breaking the layout.', 'user1', '2026-06-09 18:30:00', '2026-06-09 18:30:00'),
('Service Layer Test Data', 'BoardServiceImplTest can use this row to check DTO and VO conversion flow.', 'manager', '2026-06-10 19:40:00', '2026-06-10 19:40:00'),
('Dummy Board Post 11', 'Extra seed row for checking list rendering with multiple records.', 'user2', '2026-06-11 09:00:00', '2026-06-11 09:00:00'),
('Dummy Board Post 12', 'Extra seed row for checking table rows and simple navigation.', 'user3', '2026-06-12 09:15:00', '2026-06-12 09:15:00'),
('Dummy Board Post 13', 'Extra seed row for future writer filtering or search practice.', 'tester', '2026-06-13 09:30:00', '2026-06-13 09:30:00'),
('Dummy Board Post 14', 'Extra seed row for checking update_date before and after edits.', 'user4', '2026-06-14 09:45:00', '2026-06-14 09:45:00'),
('Dummy Board Post 15', 'Extra seed row for checking list order after delete operations.', 'admin', '2026-06-15 10:00:00', '2026-06-15 10:00:00');


SELECT * FROM tbl_board;