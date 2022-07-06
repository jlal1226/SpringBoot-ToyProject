package com.toyproject.competition.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class indexController {

    @GetMapping({"", "/"})
    public String index() {
        return "index";
    }

    @GetMapping("/test")
    public String test() {
        return "pages/test";
    }
}
