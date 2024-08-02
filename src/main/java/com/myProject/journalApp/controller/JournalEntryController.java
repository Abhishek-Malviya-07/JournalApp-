package com.myProject.journalApp.controller;

import com.myProject.journalApp.entity.JournalEntry;
import com.myProject.journalApp.entity.User;
import com.myProject.journalApp.services.JournalEntryService;
import com.myProject.journalApp.services.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    public JournalEntryService journalEntryService;
    @Autowired
    public UserService userService;

    @GetMapping
    public ResponseEntity<?> getAllJournalEntriesOfUsers() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userService.findByUserName(userName);
        List<JournalEntry> allEntriesOfUser = user.getJournalEntries();
        if (allEntriesOfUser != null && !allEntriesOfUser.isEmpty()) {
            return new ResponseEntity<>(allEntriesOfUser, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        try {
            myEntry.setDate(LocalDateTime.now());
            journalEntryService.saveEntry(myEntry, userName);
            return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("id/{myId}")
    public ResponseEntity<?> getEntryById(@PathVariable ObjectId myId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userService.findByUserName(userName);
        List<JournalEntry> userJournalEntries = user.getJournalEntries();
        List<JournalEntry> collect = userJournalEntries.stream()
                .filter(x -> x.getId().equals(myId))
                .collect(Collectors.toList());


        Optional<User> user1 = userService.getEntryById(myId);
        if (user1.isPresent()) {
            return new ResponseEntity<>(user1.get(), HttpStatus.OK);

        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @DeleteMapping("id/{myId}")
    public ResponseEntity<?> deleteEntryById(@PathVariable ObjectId myId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        journalEntryService.deleteEntryById(myId, userName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("id/{myId}")
    public ResponseEntity<JournalEntry> updateEntry(@PathVariable ObjectId myId, @RequestBody JournalEntry newEntry) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userService.findByUserName(userName);

        List<JournalEntry> collect = user.getJournalEntries().stream()
                .filter(x -> x.getId().equals(myId))
                .collect(Collectors.toList());

        if(!collect.isEmpty()){
            Optional<JournalEntry> journalEntry = journalEntryService.getEntryById(myId) ;
            if(journalEntry.isPresent()){
                JournalEntry oldEntry = journalEntry.get();
                if (oldEntry != null) {
                    if (newEntry.getTitle() != null && !newEntry.getTitle().isEmpty()) {
                        oldEntry.setTitle(newEntry.getTitle());
                    }

                    if (newEntry.getContent() != null && !newEntry.getContent().isEmpty()) {
                        oldEntry.setContent(newEntry.getContent());
                    }

                    journalEntryService.saveEntry(oldEntry);
                    return new ResponseEntity<>(oldEntry, HttpStatus.OK);
                }
            }
        }


        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

