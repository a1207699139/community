package com.chenqi.community.handler;

import com.chenqi.community.exception.NotFoundException;
import com.chenqi.community.exception.ServiceErrorException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
/**
 * @author Ardai
 * 异常拦截器
 */
@ControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ModelAndView exceptionHandler(HttpServletRequest request,Exception e){
        ModelAndView modelAndView = new ModelAndView();
        if (e instanceof ServiceErrorException || e instanceof NotFoundException){
            modelAndView.addObject("url", request.getRequestURL());
            modelAndView.addObject("exception", e.getMessage());
        }
        if (e instanceof ServiceErrorException){
            modelAndView.setViewName("500error");
        }else {
            modelAndView.setViewName("400error");
        }
        return modelAndView;
    }
}
