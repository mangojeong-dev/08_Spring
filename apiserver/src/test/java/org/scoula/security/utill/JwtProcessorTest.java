package org.scoula.security.utill;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.scoula.config.RootConfig;
import org.scoula.security.config.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {RootConfig.class, SecurityConfig.class})
@Log4j2
class JwtProcessorTest {
    @Autowired
    org.scoula.security.utill.JwtProcessor jwtProcessor;

    @Test
    void generateToken() {
        String username = "user0";
        String token = jwtProcessor.generateToken(username);
        log.info(token);
        assertNotNull(token);
    }

    @Test
    void getUsername() {
        String token = "eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJ1c2VyMCIsImlhdCI6MTc4MjY5OTg5OCwiZXhwIjoxNzgyNzAwMDc4fQ.plEJxbSOCdYS0YxeTa62Ohz4lPAYj9vRueqyLFXvc6kCh_2XhETWx9_uUhIDhYU6";
        String username = jwtProcessor.getUsername(token);
        log.info(username);
        assertNotNull(username);
    }



// 유효시간 이전이면 true 리턴
// 토큰이 해석되지 않는 경우 또는 유효 시간 만료인 경우 예외 발생
// 예외
// -ExpiredJwtException: 유효 시간 만기
// -UnsupportedJwtException: 지원하지 않은 JWT
// ⁃MalformedJwtException: 잘못된 JWT 포맷 예외
// ⁃SignatureException: 서명 불일치 예외
// -IllegalArgumentException: 잘못된 정보 포함
    @Test
    void validateToken() {
// 5분 경과 후 테스트
        String token = "eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJ1c2VyMCIsImlhdCI6MTc4MjY5OTg5OCwiZXhwIjoxNzgyNzAwMDc4fQ.plEJxbSOCdYS0YxeTa62Ohz4lPAYj9vRueqyLFXvc6kCh_2XhETWx9_uUhIDhYU6";
        boolean isValid = jwtProcessor.validateToken(token); // 3분 경과 후면 예외 발생 log.info(isValid);
        assertTrue(isValid);    // 3분전이면 true
    }

}