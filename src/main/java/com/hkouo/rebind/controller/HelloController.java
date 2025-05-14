package com.hkouo.rebind.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("/hello")
    public String hello(Model model) {
        model.addAttribute("message", "Rebind 프로젝트에 오신 것을 환영합니다!");
        return "hello"; // → hello.jsp
    }
}


