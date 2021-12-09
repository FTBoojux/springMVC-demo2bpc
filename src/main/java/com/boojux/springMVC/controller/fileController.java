package com.boojux.springMVC.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
public class fileController {
    @RequestMapping(value = "/fileup",method = RequestMethod.POST)
    public String fileup(MultipartFile file, HttpSession session){
//        获取文件名
        String filename = file.getOriginalFilename();
//        处理重名
        String name = filename.substring(filename.lastIndexOf("."));
        filename = UUID.randomUUID().toString() + name;
        ServletContext servletContext = session.getServletContext();
        String filepath = servletContext.getRealPath("img");
        File theFile = new File(filepath);
        if(!theFile.exists()){
            theFile.mkdir();
        }
        String filnalpath = filepath + File.separator + filename;
        try {
            file.transferTo(new File(filnalpath));
        } catch (IOException e) { ;
            System.out.println(e);
        }
        return "success";

    }
}
