package com.example.springapi.models;

public class UserModel {
    public UserModel(int id, String firstName, String lastName){
        this.Id = id;
        this.FirstName = firstName;
        this.LastName = lastName;
    }
    public int Id;
    public String FirstName;
    public String LastName;
}
