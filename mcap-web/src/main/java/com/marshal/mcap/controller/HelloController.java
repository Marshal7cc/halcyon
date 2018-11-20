package com.marshal.mcap.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 * author: Marshal
 * Date: 2018/11/20
 * Time: 23:52
 * Description:
 */
@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String sayHello(){
        return "hello";
    }
}
