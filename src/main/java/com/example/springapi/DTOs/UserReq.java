package com.example.springapi.DTOs;

public class UserReq {
    public UserReq(String firstName, String lastName){
        this.FirstName = firstName;
        this.LastName = lastName;
    }
    public String FirstName;
    public String LastName;
}
