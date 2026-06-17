package org.scoula.config;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * DataSource 및 커넥션 풀 테스트 클래스
 */
@ExtendWith(SpringExtension.class)              // JUnit5에서 Spring 테스트 지원
@ContextConfiguration(classes = {RootConfig.class})  // 테스트에 사용할 설정 클래스
@Log4j2
class RootConfigTest{

    // 작성된 필드와 Bean으로 등록된 객체 중 타입이 일치하는 Bean을
    // 해당 필드에 자동으로 주입(Injection)하는 어노테이션
    // == DI(Dependency Injection, 의존성 주입)
    // -> 객체를 직접 만들지 않고, Spring이 만든 걸 주입(Spring에 의존)
    @Autowired  // DataSource Bean 의존성 주입
    private DataSource dataSource;

    @Test
    @DisplayName("DataSource 연결이 된다.")
    public void dataSource() throws SQLException {
        // 커넥션 풀에서 연결 가져오기
        try (Connection con = dataSource.getConnection()) {
            log.info("=== DataSource 커넥션 풀 연결 성공 ===");
            log.info("연결 객체: {}", con);
            log.info("연결 클래스: {}", con.getClass().getName());

            // 커넥션이 HikariCP Proxy인지 확인
            if (con.getClass().getName().contains("Hikari")) {
                log.info("✅ HikariCP 커넥션 풀이 정상 작동중입니다.");
            }

        } catch (SQLException e) {
            log.error("❌ DataSource 연결 실패: {}", e.getMessage());
            throw e;
        }
    }

}