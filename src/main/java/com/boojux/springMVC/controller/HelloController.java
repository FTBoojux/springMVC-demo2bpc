package com.boojux.springMVC.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {

    //  通过@RequestMapping注解，可以通过请求路径匹配要处理的具体的请求
    //  /表示的当前工程的上下文路径
    @RequestMapping("/")
    public String index(){
        return "index";
    }

    @RequestMapping("/target")
    public String toTarget(){
        return "target";
    }

    @RequestMapping("/success")
    public String toSuccess(Model model){
        model.addAttribute("message","传递的信息");
        return "success";
    }

    @RequestMapping("/fileUpByJS")
    public String fileUpByVue(){
        return "fileByJS";
    }

}
