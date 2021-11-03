package com.example.codefellowship.Model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
public class ApplicationUser implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true)
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String dob;
    private String bio;
    @OneToMany(mappedBy = "owner",cascade=CascadeType.ALL)
    private List<Post> posts;

    @ManyToMany
    @JoinTable(
            name="posters_and_followers",
            joinColumns = { @JoinColumn(name="follower") },
            inverseJoinColumns = { @JoinColumn(name = "poster")}
    )
    Set<ApplicationUser> usersIFollow;

    @ManyToMany(mappedBy = "usersIFollow")
    Set<ApplicationUser> usersFollowingMe;

    public void followUser(ApplicationUser followedUser){
        usersIFollow.add(followedUser);
    }
    public ApplicationUser(){

    }




    public ApplicationUser( String username, String password, String firstName, String lastName, String dob, String bio) {

        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.bio = bio;



    }




    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(authority);
//        List<SimpleGrantedAuthority> grantedAuthorities = new ArrayList<SimpleGrantedAuthority>();
//        grantedAuthorities.add(simpleGrantedAuthority);
//        return grantedAuthorities;
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public int getId() {
        return id;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
    public Set<ApplicationUser> getUsersIFollow() {
        return usersIFollow;
    }

    public void setUsersIFollow(Set<ApplicationUser> usersIFollow) {
        this.usersIFollow = usersIFollow;
    }

    public Set<ApplicationUser> getUsersFollowingMe() {
        return usersFollowingMe;
    }

    public void setUsersFollowingMe(Set<ApplicationUser> usersFollowingMe) {
        this.usersFollowingMe = usersFollowingMe;
    }
    //    public String getAuthority() {
//        return authority;
//    }

//    public void setAuthority(String authority) {
//        this.authority = authority;
//    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    @Override
    public String toString() {
        return "ApplicationUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dob='" + dob + '\'' +
                ", bio='" + bio + '\'' +
                '}';
    }
}