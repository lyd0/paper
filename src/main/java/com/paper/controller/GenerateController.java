package com.paper.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GenerateController {

//    到首页
    @GetMapping("/")
    public String toIndexPage() {
        return "index";
    }

//  到生成页面
    @GetMapping("/generate")
    public String toGeneratePage() {
        return "generate";
    }


}
