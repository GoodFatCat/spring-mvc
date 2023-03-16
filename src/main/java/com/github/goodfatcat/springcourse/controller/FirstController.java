package com.github.goodfatcat.springcourse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/first")
public class FirstController {

    @GetMapping("/hello")
    public String helloPage(@RequestParam(value = "name", required = false) String name,
                            @RequestParam(value = "surname", required = false) String surname,
                            Model model){
//        RequestParam is obligatory. If param isn't used then you'll get an error
//        But you can use required=false, so you can don't use this parameter.

//        This way we use ServletRequest to get parameters
//        HttpServletRequest request;
//        String name = request.getParameter("name");
//        String surname = request.getParameter("surname");

        model.addAttribute("message", "Hello " + name + " " + surname);

        return "first/hello";
    }

    @GetMapping("/goodbye")
    public String goodByePage() {
        return "first/goodbye";
    }
}
