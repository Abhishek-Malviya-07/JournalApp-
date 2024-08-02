package com.myProject.journalApp.services;

import com.myProject.journalApp.entity.User;
import com.myProject.journalApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public void saveNewUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
         user.setRoles(Arrays.asList("USER"));
        userRepository.save(user);
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }


    public List<User> getAll() {
        return userRepository.findAll();
    }

    public Optional<User> getEntryById(ObjectId objectId) {
        return userRepository.findById(objectId);
    }

    public void deleteEntryById(ObjectId objectId) {
        userRepository.deleteById(objectId);
    }

    public User findByUserName(String username) {
        return userRepository.findByUserName(username);
    }

}
