package com.servixo.entity;

import jakarta.persistence.*;

@Entity
public class Professional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String category;
    private int experience;
    private double rating;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Constructors
    public Professional() {}

    public Professional(String category, int experience, double rating, User user) {
        this.category = category;
        this.experience = experience;
        this.rating = rating;
        this.user = user;
    }

    // Getters & Setters
    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getCategory() { return category; }

    public void setCategory(String category) { this.category = category; }

    public int getExperience() { return experience; }

    public void setExperience(int experience) { this.experience = experience; }

    public double getRating() { return rating; }

    public void setRating(double rating) { this.rating = rating; }

    public User getUser() { return user; }

    public void setUser(User user) { this.user = user; }
}