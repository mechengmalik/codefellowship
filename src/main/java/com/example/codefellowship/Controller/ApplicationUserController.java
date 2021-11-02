package com.example.codefellowship.Controller;

import com.example.codefellowship.Model.ApplicationUser;
import com.example.codefellowship.Repository.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;

@Controller
public class ApplicationUserController {


    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @Autowired
    PasswordEncoder encoder;






    @GetMapping("/signup")
    public String signup(){
        return "signup";
    }



    @PostMapping("/signup")
    public RedirectView signUpUser(@RequestParam(value="username") String username,
                                   @RequestParam(value="password") String password,
                                   @RequestParam(value = "firstname")String firstName,
                                   @RequestParam(value = "lastname")String lastName,
                                   @RequestParam(value = "dob") String dob,
                                   @RequestParam(value = "bio")String bio)
    {
        ApplicationUser newAppUser = new ApplicationUser(username, encoder.encode(password),firstName,lastName,dob,bio);
//        System.out.println(newAppUser.toString());
        applicationUserRepository.save(newAppUser);
        return new RedirectView("/login");
    }


    @GetMapping("/login")
        public String login(){

        return "login.html";
        }

    @GetMapping("/profile")
    public String getProfile(Model m, Principal principal) {
        try {
            ApplicationUser user = applicationUserRepository.findByUsername(principal.getName());
            m.addAttribute("user", user);
            return "profile";
        } catch (Exception e) {
            return "profile";
        }
    }

    @GetMapping("/profile/{id}")
    public String getUserPage(Principal p,Model m, @PathVariable int id){
        try {
            String visitors = p.getName();
            ApplicationUser owner = applicationUserRepository.findUserById(id);
            m.addAttribute("owner", owner);
            m.addAttribute("visitor", visitors);
            return "users";
        }
        catch(Exception e){
            return "users";
        }
    }






    @PutMapping("/profile/{id}")
    public RedirectView updateProfile(@PathVariable int id , Principal principal){
    ApplicationUser loggedInUser = applicationUserRepository.findByUsername((principal.getName()));
    if(loggedInUser.getId() != id){
        return  new RedirectView("/error");
    }
     else{
         applicationUserRepository.save(loggedInUser);
         return new RedirectView("/profile/"+id) ;
    }



    }

}
//    @AuthenticationPrincipal ApplicationUser loggedInUser




