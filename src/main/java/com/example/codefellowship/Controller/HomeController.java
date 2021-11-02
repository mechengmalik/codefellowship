package com.example.codefellowship.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class HomeController {
    @GetMapping("/")
    public String getHomePage(Principal p, Model model) {
        if (p == null) {
            return "index.html";
        } else {
            model.addAttribute("username", p.getName());
            return "home.html";
        }


    }

}
