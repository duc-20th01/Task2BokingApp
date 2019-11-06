package com.example.bookingapp.model;

import java.io.Serializable;

public class Post implements Serializable {
    private int id;
    private String name;
    private String skill;
    private String description;
    private int quantity;
    private String address;
    private String createdDate;
    private String authorId;

    public Post(){
    }

    public Post(String name, String skill, String description, int quantity, String address, String createdDate, String authorId) {
        this.name = name;
        this.skill = skill;
        this.description = description;
        this.quantity = quantity;
        this.address = address;
        this.createdDate = createdDate;
        this.authorId = authorId;
    }

    public Post(int id, String name, String skill, String description, int quantity, String address, String createdDate, String authorId) {
        this.id = id;
        this.name = name;
        this.skill = skill;
        this.description = description;
        this.quantity = quantity;
        this.address = address;
        this.createdDate = createdDate;
        this.authorId = authorId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }
}
