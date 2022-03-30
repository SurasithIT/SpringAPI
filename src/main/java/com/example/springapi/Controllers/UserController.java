package com.example.springapi.Controllers;

import com.example.springapi.DTOs.UserReq;
import com.example.springapi.Models.UserModel;
import com.example.springapi.db.Db;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/Users")
public class UserController {
    @GetMapping
    public ResponseEntity<ArrayList<UserModel>> GetUsers(){
        return new ResponseEntity(Db.users, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<UserModel> GetUserById(@PathVariable int id){
        UserModel user = Db.users.stream()
                .filter(_user -> _user.Id == id)
                .findAny()
                .orElse(null);
        return new ResponseEntity(user, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserModel> AddUser(@RequestBody UserReq req){
        if(req.FirstName == null || req.LastName == null){
            return new ResponseEntity("FirstName and LastName is required!", HttpStatus.BAD_REQUEST);
        }

        int _id = Db.users.size();
        UserModel user = new UserModel(++_id, req.FirstName, req.LastName);
        Db.users.add(user);
        return new ResponseEntity(user, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<UserModel> UpdateUser(@PathVariable int id, @RequestBody UserReq req){
        if(req.FirstName == null || req.LastName == null){
            return new ResponseEntity("FirstName and LastName is required!", HttpStatus.BAD_REQUEST);
        }

        UserModel user = Db.users.stream()
                .filter(_user -> _user.Id == id)
                .findAny()
                .orElse(null);
        if(user == null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        user.FirstName = req.FirstName;
        user.LastName = req.LastName;
        return new ResponseEntity(user, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity DeleteUser(@PathVariable int id){
        UserModel user = Db.users.stream()
                .filter(_user -> _user.Id == id)
                .findAny()
                .orElse(null);

        if(user == null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        Db.users.remove(user);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
