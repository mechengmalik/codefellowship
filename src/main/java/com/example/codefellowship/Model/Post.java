package com.example.codefellowship.Model;

import javax.persistence.*;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @ManyToOne
    private ApplicationUser owner;
    private String body;
    private String createdAt;

    public Post() {

    }

    public Post(String body, String createdAt, ApplicationUser owner) {
        this.body = body;
        this.createdAt = createdAt;
        this.owner = owner;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ApplicationUser getOwner() {
        return owner;
    }

    public void setOwner(ApplicationUser owner) {
        this.owner = owner;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String toString() {
        return String.format("my post ", this.createdAt, this.owner.getUsername(), this.body);
    }
}