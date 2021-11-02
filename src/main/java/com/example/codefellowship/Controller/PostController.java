package com.example.codefellowship.Controller;

import com.example.codefellowship.Model.ApplicationUser;
import com.example.codefellowship.Model.Post;
import com.example.codefellowship.Repository.ApplicationUserRepository;
import com.example.codefellowship.Repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class PostController {

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @Autowired
    PostRepository postRepository;


    @GetMapping("/addPost")
    public String showAddPost(Principal p, Model m) {
        if (p != null) {
            m.addAttribute("username", p.getName());
        }
        return "addPost";
    }

    @PostMapping("/addPost")
    public RedirectView addPost(Principal p, String body) {
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        Date timeStamp = new Date();

        ApplicationUser User = applicationUserRepository.findByUsername(p.getName());
        Post post = new Post(body, dateFormat.format(timeStamp), User);
        postRepository.save(post);
        return new RedirectView("/profile/" + User.getId());
    }
}