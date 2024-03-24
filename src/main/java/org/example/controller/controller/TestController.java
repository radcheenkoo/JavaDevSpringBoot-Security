package org.example.controller.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Validated
@Controller
@RequestMapping("/main/homeworkMod15")
public class TestController {
    @GetMapping("/test")
    public ModelAndView getHelloWord(){
        String helloWorld = "Hello World!";
        ModelAndView res = new ModelAndView("test");

        res.addObject("helloWorld",helloWorld);
        return res;
    }
}
