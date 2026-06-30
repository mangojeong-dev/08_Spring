package org.scoula.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Log4j2
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {

        // Model : 데이터 전달용 객체
        // -> K:V 형식으로 데이터 담아서 전달
        // -> 기본적으로 request scope

        model.addAttribute("name", "홍길동");
        // View로 전달할 데이터 Model에 추가

        // Spring에서 forward 하는 방법
        // -> webapp 폴더를 기준으로 요청 위임할
        //    JSP 파일 경로를 return 하면 된다.

        // 단, ServletConfig에 작성된 prefix, suffix 제외하고 작성!!
        // resolver.setPrefix("/WEB-INF/views/");
        // resolver.setSuffix(".jsp");

        // prefix + 리턴값 + suffix로 경로 완성
        // -> View Resolver
        return "index";		// View의 이름
    }

}