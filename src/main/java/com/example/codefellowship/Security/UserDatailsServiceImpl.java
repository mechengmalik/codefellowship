package com.example.codefellowship.Security;


import com.example.codefellowship.Model.ApplicationUser;
import com.example.codefellowship.Repository.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service

public class UserDatailsServiceImpl implements UserDetailsService {
    @Autowired
    ApplicationUserRepository applicationUserRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ApplicationUser applicationUser= applicationUserRepository.findByUsername(username);
        if(applicationUser==null){
            throw new UsernameNotFoundException("the user name "+username+"not found");
        }
        return applicationUser;
    }

}
