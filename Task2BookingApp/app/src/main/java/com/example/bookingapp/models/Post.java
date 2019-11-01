package com.example.bookingapp.models;

import java.io.Serializable;

public class Post implements Serializable {
    public Post() {
    }

    public Post(int id, String name, String skill, String descreption, int quantity, String address, String createdDate, String email) {
        this.id = id;
        this.name = name;
        this.skill = skill;
        this.descreption = descreption;
        this.quantity = quantity;
        this.address = address;
        this.createdDate = createdDate;
        this.email = email;
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

    public String getDescreption() {
        return descreption;
    }

    public void setDescreption(String descreption) {
        this.descreption = descreption;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private int id;
    private String name;
    private String skill;
    private String descreption;
    private int quantity;
    private String address;
    private String createdDate;
    private String email;
}
