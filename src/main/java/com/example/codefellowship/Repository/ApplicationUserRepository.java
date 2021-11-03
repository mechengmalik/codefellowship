package com.example.codefellowship.Repository;

import com.example.codefellowship.Model.ApplicationUser;
import org.springframework.data.repository.CrudRepository;

public interface ApplicationUserRepository extends CrudRepository<ApplicationUser,Integer> {
     ApplicationUser findByUsername(String username);


     ApplicationUser findUserById(int id);
     ApplicationUser getOne(int followUser);
}
