package com.boojux.springMVC.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class errorController {
    @ExceptionHandler(ArithmeticException.class)
    public String handleError(Exception ee, Model model){
        model.addAttribute("ex",ee);
        return "error";
    }
}
