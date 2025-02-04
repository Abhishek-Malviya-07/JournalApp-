package com.myProject.journalApp.entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

@Data
@NoArgsConstructor
@Document(collection = "journal_entries")
public class JournalEntry {

   @Id
   private ObjectId id;

   @NonNull
   private String title;

   private String content;

   private LocalDateTime date;



}
