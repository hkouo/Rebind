package com.hkouo.rebind.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping("/admin")
    public String adminHome() {
        return "admin/adminHome"; // /WEB-INF/views/admin/adminHome.jsp
    }
}
