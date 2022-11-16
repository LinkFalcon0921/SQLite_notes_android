package com.flintcore.my_notes_android.database.tables;

import java.io.Serializable;

public class Note implements Serializable {

    private int id;

    private String subject;
    private String description;
//    private LocalDateTime dateCreated;


    public Note() {
//        this.dateCreated = LocalDateTime.now();
    }

    public Note(String subject, String description) {
        this();
        this.subject = subject;
        this.description = description;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


//    public LocalDateTime getDateCreated() {
//        return dateCreated;
//    }
//
//    public void setDateCreated(LocalDateTime dateCreated) {
//        this.dateCreated = dateCreated;
//    }
}
