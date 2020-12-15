package com.chenqi.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Ardai
 */
@Controller
public class IndexController {

    @GetMapping("/")
    public ModelAndView index(ModelAndView modelAndView){
        modelAndView.setViewName("index");
        return modelAndView;
    }
    @GetMapping("/toLogin")
    public ModelAndView toLogin(ModelAndView modelAndView){
        modelAndView.setViewName("login");
        return modelAndView;
    }
    @GetMapping("/toRegister")
    public ModelAndView toRegister(ModelAndView modelAndView){
        modelAndView.setViewName("register");
        return modelAndView;
    }
}
