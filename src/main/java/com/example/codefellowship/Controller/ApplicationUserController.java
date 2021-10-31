package com.example.codefellowship.Controller;

import com.example.codefellowship.Model.ApplicationUser;
import com.example.codefellowship.Repository.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

public class ApplicationUserController {

    @Autowired
    ApplicationUserRepository applicationUserRepository;
    @Autowired
    PasswordEncoder encoder;

    @GetMapping("/")
    public String appStart(){
        return"opening.html";
    }
    @GetMapping("/signup")
    public String signup(){
        return "signup";
    }



    @PostMapping("/signup")
    public String signUpUser(@RequestParam String username,
                             @RequestParam String password,
                             @RequestParam String firstName,

                             @RequestParam String lastName,
                             @RequestParam String dob,
                             @RequestParam String bio)
    {
        ApplicationUser appUser = new ApplicationUser(username, encoder.encode(password),firstName,lastName,dob,bio);
        applicationUserRepository.save(appUser);
        return "login";
    }


    @GetMapping("/login")
    public String login(){
        return "login.html";

    }
//    @GetMapping("/users/{id}")

}
