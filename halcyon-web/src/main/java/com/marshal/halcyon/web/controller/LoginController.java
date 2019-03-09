package com.marshal.halcyon.web.controller;

import org.apache.http.HttpResponse;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @auth: Marshal
 * @date: 2019/3/8
 * @desc:
 */
@Controller
public class LoginController {

    @RequestMapping("/login")
    public ModelAndView login(ModelAndView mv,
                              HttpServletRequest request,
                              HttpServletResponse response) {
        mv.setViewName("index");
        mv.addObject("userName", request.getSession().getAttribute("userName"));
        return mv;
    }
}
