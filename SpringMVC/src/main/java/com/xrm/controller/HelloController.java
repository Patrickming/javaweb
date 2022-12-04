package com.xrm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Date:2022/7/7
 * Author:ybc
 * Description:
 */
@Controller
public class HelloController {

    @RequestMapping("/")
    public String protal(){
        //将逻辑视图返回//去掉物理视图的前缀后缀就是
        return "index";
    }

    @RequestMapping("/hello")
    public String hello(){
        return "success";
    }

}
