package org.scoula.security.handler;

import lombok.extern.slf4j.Slf4j;
import org.scoula.security.util.JsonResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
// AccessDeniedHandler : 인증, 로그인 등은 완료되었지만 적절한 권한을 가지지 않은 사용자가 다른 api 요청을 보낸 경우 실행되는 인터페이스
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(
            HttpServletRequest request,
            HttpServletResponse response,
            AccessDeniedException accessDeniedException
    ) throws IOException, ServletException {

        log.error("========== 인가 에러 ============");
        JsonResponse.sendError(
                response,
                HttpStatus.FORBIDDEN,
                "권한이 부족합니다."
        );
    }
}