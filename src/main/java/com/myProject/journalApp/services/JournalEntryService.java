package com.myProject.journalApp.services;

import com.myProject.journalApp.entity.JournalEntry;
import com.myProject.journalApp.entity.User;
import com.myProject.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;

    @Transactional
    public void saveEntry(JournalEntry journalEntry, String userName) {
        try {
            User user = userService.findByUserName(userName);

            JournalEntry save = journalEntryRepository.save(journalEntry);
            user.getJournalEntries().add(save);
            userService.saveUser(user);
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException("An error occured  while save your entry" ,e);
        }
    }

    public void saveEntry(JournalEntry journalEntry) {

        journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getAll() {
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> getEntryById(ObjectId objectId) {
        return journalEntryRepository.findById(objectId);
    }

    @Transactional
    public void deleteEntryById(ObjectId objectId, String userName) {
        try {
            User user = userService.findByUserName(userName);
            boolean removed = user.getJournalEntries().removeIf(x -> x.getId().equals(objectId));
            if (removed) {
                userService.saveUser(user);
                journalEntryRepository.deleteById(objectId);
            }
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException("An error occured  while deleting your entry" ,e);
        }
    }


}
