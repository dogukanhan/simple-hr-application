package com.dogukanhan.kodgemisi.simplehr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller("/")
public class IndexController {

    @GetMapping
    public String getIndex(){
        return "index";
    }
}
