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
import java.util.List;

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
            return "error";
        }
    }

    @GetMapping("/user/{id}")
    public String getUserPage(Principal p,Model m, @PathVariable int id){
        try {
            ApplicationUser user = applicationUserRepository.findByUsername(p.getName());

//            String visitors = p.getName();
            ApplicationUser owner = applicationUserRepository.findUserById(id);
            m.addAttribute("owner", owner);
            m.addAttribute("visitor", user);
            return "users";
        }
        catch(Exception e){
            return "users";
        }
    }

    @GetMapping("/users")
    public String getAllUsers( Model model, Principal principal) {
        List<ApplicationUser> allUsers = (List<ApplicationUser>) applicationUserRepository.findAll();
        ApplicationUser me = applicationUserRepository.findByUsername(principal.getName());
        model.addAttribute("users", allUsers);
        model.addAttribute("me", me);
        return "allUsers";
    }

    @PostMapping ("/follow")
    public RedirectView followUser(Principal p, int followUser) {
        ApplicationUser follower = applicationUserRepository.findByUsername(p.getName());
        ApplicationUser poster = applicationUserRepository.getOne(followUser);
        follower.followUser(poster);

        applicationUserRepository.save(follower);

        return new RedirectView("/Profile");
    }

    @GetMapping ("/usersIfollow")
    public String usersIfollow(Principal p,Model m) {
        ApplicationUser myObject = applicationUserRepository.findByUsername(p.getName());
        m.addAttribute("userForOwner", myObject);
        return "usersIFollowed";
    }
    @GetMapping ("/usersFollowingMe")
    public String usersFollowingMe(Principal p,Model m) {
        ApplicationUser myObject = applicationUserRepository.findByUsername(p.getName());
        m.addAttribute("userForOwner", myObject);
        return "userFollowingMe";
    }









//    @PutMapping("/profile/{id}")
//    public RedirectView updateProfile(@PathVariable int id , Principal principal){
//    ApplicationUser loggedInUser = applicationUserRepository.findByUsername((principal.getName()));
//    if(loggedInUser.getId() != id){
//        return  new RedirectView("/error");
//    }
//     else{
//         applicationUserRepository.save(loggedInUser);
//         return new RedirectView("/profile/"+id) ;
//    }
//
//
//
//    }

}
//    @AuthenticationPrincipal ApplicationUser loggedInUser




