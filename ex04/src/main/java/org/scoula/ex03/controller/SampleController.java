package org.scoula.ex03.controller;

import lombok.extern.log4j.Log4j2;
import org.scoula.ex03.dto.SampleDTO;
import org.scoula.ex03.dto.SampleDTOList;
import org.scoula.ex03.dto.TodoDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

// 현재 클래스가 컴포넌트임을 명시 -> 요청/응답 처리 + Bean 등록
@Controller                    // Spring MVC 컨트롤러임을 선언 - 컴포넌트 스캔 대상
@RequestMapping("/sample")     // 클래스 레벨 - 모든 메서드의 기본 URL 경로 설정
@Log4j2                        // 로깅을 위한 Lombok 어노테이션
public class SampleController {

    @RequestMapping("")        // 메서드 레벨 - 세부 경로 ("" = 기본 경로만 사용)
    public void basic() {      // void 리턴 = RequestMapping과 동일한 이름의 JSP 자동 매핑
        log.info("basic............");
        // /sample 요청 시 /WEB-INF/views/sample.jsp로 forward
    }


    // HTTP 메서드를 명시적으로 지정하는 방식 - 다중 메서드 허용
    // /sample/basic으로 (GET or POST)요청이 오면 해당 메서드와 연결한다
    @RequestMapping(value = "/basic", method = {RequestMethod.GET, RequestMethod.POST})
    public void basicGet() {
        log.info("basic get............");
        // GET과 POST 요청 모두 처리 가능
    }

    // GET 요청만 처리 - 조회 작업에 사용 (Safe, Idempotent)
    @GetMapping("/basicOnlyGet")
    public void basicGet2() {
        log.info("basic get only get............");
        // 데이터 조회, 페이지 표시 등 안전한 작업
    }


    @GetMapping("/ex01")
    public String ex01(@ModelAttribute SampleDTO dto) {  // HandlerAdapter가 자동으로 객체 생성 및 프로퍼티 바인딩
        log.info("" + dto);   // 바인딩된 데이터 로그 출력으로 확인

        // parameter 전달 방법
        // 1. @ModelAttribute를 이용한 방법
        // * @ModelAttribute는 생략 가능
        // - DTO(또는 VO)와 같이 사용하는 경우

        // - 전달받은 파라미터의 name 속성값이
        //   같이 사용되는 DTO의 필드명과 같다면
        //   자동으로 setter 호출해서 필드에 값 세팅

        // * @ModelAttribute를 이용해 값이 세팅된 객체를 '커맨드 객체'라고 한다


        // * @ModelAttribute 사용 시 주의사항 *
        //   - DTO에 기본 생성자가 필수!
        //   - DTO에 Setter 필수!

        return "sample/ex01"; // ViewResolver에 의해 /WEB-INF/views/sample/ex01.jsp로 forward
    }


    @GetMapping("/ex02")
    public String ex02(
            @RequestParam("name") String name,    // 파라미터명 "name"을 String으로 바인딩
            @RequestParam("age") int age) {       // 파라미터명 "age"를 int로 자동 변환
        log.info("name: " + name);
        log.info("age: " + age);

//         파라미터 전달 방법
//         2. @RequestParam을 이용한 방법
//         - request 객체를 이용한 파라미터 전달 어노테이션
//         - 매개변수 앞에 해당 어노테이션을 작성하면 매개변수에 값이 주입됨


        // * 파라미터의 name 속성과 매개변수명이 같으면
        //   @RequestParam 생략 가능!

        return "sample/ex02";
    }

    // @RequestParam 옵션 활용 - 파라미터 누락 및 기본값 처리
    @GetMapping("/ex02-advanced")
    public String ex02Advanced(
            @RequestParam(value = "name", required = false, defaultValue = "익명") String name,
            @RequestParam(value = "age", required = false, defaultValue = "10") int age) {
        // value : 전달받은 파라미터의 name 속성값
        // required=false: 파라미터가 없어도 에러 발생하지 않음
        // defaultValue: 파라미터가 없을 때 사용할 기본값 (문자열로 지정, 자동 형변환)

        log.info("name : " + name + ", age : " + age);

        return "sample/ex02";
    }


    @GetMapping("/ex02Bean")
    public String ex02Bean(SampleDTOList list) {
        log.info("list dtos: " + list);
        return "ex02Bean";
        // 복합 객체 내부의 리스트도 자동 바인딩 처리
    }

    @GetMapping("/ex02List")
    public String ex02List(SampleDTOList ids) {
        log.info("ids dtos: " + ids);
        return "ex02List";
        // 복합 객체 내부의 리스트도 자동 바인딩 처리
    }


    @GetMapping("/ex03")
    public String ex03(TodoDTO todo) {
        log.info("todo: " + todo);
        return "ex03";
        // @DateTimeFormat에 지정된 패턴에 맞는 문자열만 Date로 변환
    }


    @GetMapping("/ex04")
    // int page는 @RequestParam이 생략된 거라서 model에 담으려면 ModelAttribute에 담아야 한다
    public String ex04(SampleDTO dto, @ModelAttribute("page") int page) {

        // @ModelAttribute 역할
        // 1. 요청 파라미터를 객체(또는 변수)에 바인딩
        // 2. 바인딩된 객체를 Model에 자동으로 등록

        // @ModelAttribute("page") int page
        // @RequestParam + model.addAttribute("page",page) 역할

        log.info("dto: " + dto);
        log.info("page: " + page);
        return "sample/ex04";
        // @ModelAttribute로 기본 자료형도 Model에 추가하여 뷰에서 접근 가능
    }


}