package org.scoula.ex03.controller;

import lombok.extern.log4j.Log4j2;
import org.scoula.ex03.dto.SampleDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.Arrays;

@Controller
@RequestMapping("/sample")
@Log4j2
public class SampleController {
    // get/post 방식 상관없이 모든 방식에 동일한 처리하고 싶을 때 ""
    @RequestMapping("")     // url : /sample
    public void basic() {
        log.info("#########basic#########");
    }

    @RequestMapping(value = "/basic", method = {RequestMethod.GET, RequestMethod.POST})  // url: /sample/basic
    public void basicGet() {
        log.info("#########basic get#########");
    }

    @GetMapping("/basicOnlyGet")  // url: /sample/basicOnlyGet
    public void basicGet2() {
        log.info("#########basic get only get#########");
    }

    @GetMapping("/ex02")
    public String ex02(@RequestParam("name") String name,
                     @RequestParam("age")  int age) {
        log.info("#########ex02#########");
        log.info("name: " + name);
        log.info("age: " + age);
        return "ex02";
    }

    @GetMapping("/ex03")
    public String ex03(SampleDTO dto) {
        log.info("" + dto);
        return "ex03";
    }
    @GetMapping("/ex03List")
    public String ex03List(@RequestParam("ids") ArrayList<String> ids) {
        log.info("ids: " + ids);
        return "ex03List";
    }

    @GetMapping("/ex03Array")
    public String ex03Array(@RequestParam("ids") String[] ids) {
        log.info("array ids: " + Arrays.toString(ids));
        return "ex03Array";
    }

    @GetMapping("/ex04")
    public String ex04(SampleDTO sampleDTO, @ModelAttribute("page") int page) {
        log.info("dto: " + sampleDTO);
        log.info("page: " + page);
        return "sample/ex04";
    }



    @GetMapping("/ex06")
    public String ex06(RedirectAttributes ra) {
        log.info("/ex06........");
        ra.addAttribute("name", "AAA");
        ra.addAttribute("age", 10);
        return "redirect:/sample/ex06-2";
    }

    @GetMapping("/ex07")
    public @ResponseBody SampleDTO ex07() {
        log.info("/ex07........");
        SampleDTO dto = new SampleDTO(); dto.setAge(10);
        dto.setName("홍길동");
        return dto;
    }

    @GetMapping("/ex08")
    public ResponseEntity<String> ex08() {
        log.info("/ex08..........");
        // {"name": "홍길동"}
        String msg = "{\"name\": \"홍길동\"}";
        HttpHeaders header = new HttpHeaders();
        header.add("Content-Type", "application/json;charset=UTF-8");
        return new ResponseEntity<>(msg, header, HttpStatus.OK);
    }

}
