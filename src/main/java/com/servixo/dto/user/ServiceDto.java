package com.servixo.dto.user;

public class ServiceDto {

    private Long id;
    private String title;
    private String description;
    private double price;
    private String status;

    public ServiceDto() {}

    public ServiceDto(Long id, String title, String description, double price, String status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.status = status;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}