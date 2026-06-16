package org.scoula.ex03.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

}
