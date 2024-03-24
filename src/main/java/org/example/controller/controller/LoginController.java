package org.example.controller.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping
public class LoginController {

    @RequestMapping("/")
    public RedirectView defaultHome(HttpServletResponse response) {

        return new RedirectView("/note/list");
    }

}
