package org.scoula.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 홈(메인) 페이지 컨트롤러
 * - 애플리케이션의 메인 페이지와 기본 요청을 처리하는 컨트롤러
 *
 * 주요 기능:
 * - 루트 경로("/") 요청 처리
 * - 홈페이지 렌더링
 * - 기본 진입점 역할
 *
 * @Controller
 * - @ComponentScan 어노테이션으로 스캔된 경우 자동으로 Bean 등록되는
 *   @Component 어노테이션의 하위 어노테이션
 * - Spring MVC 컨트롤러임을 명시
 *
 * @Log4j2
 * - Lombok을 이용해 log 관련 필드를 생성하는 어노테이션
 * - private static final org.apache.logging.log4j.Logger log
 *   = org.apache.logging.log4j.LogManager.getLogger(HomeController.class)
 */
@Controller // Spring MVC 컨트롤러로 등록
@Log4j2
public class HomeController {


    @GetMapping("/")
    // Model : 데이터 전달용 객체
    // -> K : V 형식으로 데이터 담아서 전달
    // -> 기본적으로 request scope
    public String home(Model model) {
        log.info("================> HomeController /");
        model.addAttribute("name", "홍길동"); // View로 전달할 데이터 Model에 추가

        // spring에서 forward하는 방법
        // -> webapp 폴더를 기준으로 요청 위임할 JSP 파일 경로를 return하면 된다

        // 단, ServletConfig에 작성한 prefix는 제외하고 작성해야 한다
        // resolver.setPrefix("/WEB-INF/views/");        // JSP 파일 위치 접두사
        // resolver.setSuffix(".jsp");                   // JSP 확장자 접미사
        // prefix + return 값 + suffix로 경로 완성 <- ViewResolver가
        return "index"; // View의 이름 (ServletConfig의 ViewResolver에 의해 /WEB-INF/views/index.jsp로 변환)
    }
}
