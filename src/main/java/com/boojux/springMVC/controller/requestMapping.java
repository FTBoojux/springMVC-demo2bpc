package com.boojux.springMVC.controller;

import com.atguigu.mvc.Bean.Book;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.Map;
import java.util.UUID;

@Controller
//@RequestMapping("/ha")
public class requestMapping {
    @RequestMapping("/ho")
    public String getH(){
        return "index";
    }
    @RequestMapping(
            value = {"/post1","/post2"},
            method = {RequestMethod.GET,RequestMethod.POST}
    )
    @ResponseBody
    public String methods(){
//        String str = "method";
        return "str";
    }
//    ?:任意单个字符
//    *:任意字符
//    **:任意层数的目录(只能以/**/xxx形式使用
    @RequestMapping("/a?a")
    @ResponseBody
    public String zhanweifu(){
        return "占位符，呜呼呼";
    }

    @RequestMapping("/getID/{id}/{name}")
    @ResponseBody
    public String getID(@PathVariable("id")String id,@PathVariable("name")String name){
        return "id:" + id + ",name:" + name;
    }

    @RequestMapping(value = "/put",method = RequestMethod.PUT)
    @ResponseBody
    public String handlePut(@RequestBody Map<String,String> params){
//        System.out.println(message.get("message"));
        System.out.println(params);
//        System.out.println(with);
//        Map<String,String> params = message;
//        System.out.println(message.get("message"));
        return params.get("message");
    }
    @RequestMapping(value = "/ReEntity",method = RequestMethod.POST)
    @ResponseBody
    public String getReEntity(RequestEntity<String> ReEntity){
        System.out.println("Header:" +  ReEntity.getHeaders());
        System.out.println("Body:" + ReEntity.getBody());
//        Map<String,String> map = ReEntity.getBody();
        return "success";
    }

    @RequestMapping(value = "/testJSON",method = RequestMethod.POST)
    @ResponseBody
    public Book tesJSON(@RequestBody Map<String,String> data){
        System.out.println(data);
        return new Book("三体",123);
    }

    @RequestMapping(value = "/download",method = RequestMethod.GET)
    public ResponseEntity<byte[]> testResponseEntity(HttpSession httpSession){
        ServletContext servletContext = httpSession.getServletContext();
//        获取文件路径
        String filePath = servletContext.getRealPath("/WEB-INF/img/p01.jpg");
//        创建输入流
        try {
            InputStream is = new FileInputStream(filePath);
//            创建字节数组
            byte[] bytes = new byte[is.available()];
//            读取流到字节数组
            is.read(bytes);
//            设置响应头信息
            MultiValueMap<String,String> headers = new HttpHeaders();
//            设置下载方式及下载文件名字
            headers.add("Content-Disposition", "attachment;filename=1.jpg");
//            设置状态响应码
//            创建ResponseEntity对象
            ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(bytes,headers,HttpStatus.OK);
//            关闭输入流
            is.close();
            return responseEntity;
        } catch (FileNotFoundException e) {
            System.out.println(e);
            System.out.println("文件读取出错！");
        } catch (IOException e) {
            System.out.println("字节数组创建出错");
        }
        return null;
    }

//    @RequestMapping("/error")
    @RequestMapping("/errorByNote")
    public String error(){
        int i = 1/0;
        return "index";
    }

}
