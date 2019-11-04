package com.example.bookingapp.model;

import java.io.Serializable;

public class Member implements Serializable {

    private int id;
    private String name;
    private String email;
    private String password;
    private String address;
    private String phone;
    private int type;

    public Member(String name, String email, String password, String address, String phone, int type){
        this.name=name;
        this.email=email;
        this.password=password;
        this.address=address;
        this.phone=phone;
        this.type=type;
    }
    public Member(int id, String name, String email, String password, String address, String phone, int type){
        this.id=id;
        this.name=name;
        this.email=email;
        this.password=password;
        this.address=address;
        this.phone=phone;
        this.type=type;
    }

    public Member(String email, String password){
        this.email=email;
        this.password=password;
    }

    public Member() {
    }

    public Member(int id, String name, String address, String phone, int type){
        this.id=id;
        this.name=name;
        this.address=address;
        this.phone=phone;
        this.type=type;
    }

    public Member(int id, String password){
        this.id=id;
        this.password=password;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
