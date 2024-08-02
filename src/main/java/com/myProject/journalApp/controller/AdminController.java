package com.myProject.journalApp.controller;


import com.myProject.journalApp.entity.User;
import com.myProject.journalApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    public UserService userService;

    @GetMapping("/getAllUsers")
    public ResponseEntity<?> getAll(){
        List<User> allUser = userService.getAll();
        if(allUser != null && !allUser.isEmpty()){
            return new ResponseEntity<>(allUser , HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/create-admin-user")
    public void createUser(@RequestBody User user){
        userService.saveAdmin(user);
    }
}
