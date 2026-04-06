package com.servixo.entity;

import jakarta.persistence.*;

@Entity
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int rating;
    private String comment;

    @ManyToOne
    private User user;

    @ManyToOne
    private Professional professional;

    // Constructors
    public Feedback() {}

    public Feedback(int rating, String comment, User user, Professional professional) {
        this.rating = rating;
        this.comment = comment;
        this.user = user;
        this.professional = professional;
    }

    // Getters & Setters
    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public int getRating() { return rating; }

    public void setRating(int rating) { this.rating = rating; }

    public String getComment() { return comment; }

    public void setComment(String comment) { this.comment = comment; }

    public User getUser() { return user; }

    public void setUser(User user) { this.user = user; }

    public Professional getProfessional() { return professional; }

    public void setProfessional(Professional professional) { this.professional = professional; }
}