package com.myProject.journalApp.controller;
import com.myProject.journalApp.entity.User;
import com.myProject.journalApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class PublicController{

    @Autowired
    public UserService userService;

    @PostMapping("/createUser")
    public void createUser(@RequestBody User user) {
        userService.saveNewUser(user);
    }

}
