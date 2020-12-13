package com.chenqi.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 12076
 */
@Controller
@RequestMapping("/hello")
public class GreetingController {

    @GetMapping("/test")
    public String hell(@RequestParam(name = "name") String name, Model model){
        model.addAttribute("name", name);
        return "hello";
    }

}
