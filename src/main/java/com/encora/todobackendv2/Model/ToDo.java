package com.encora.todobackendv2.Model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import java.time.Duration;
import com.fasterxml.jackson.annotation.JsonFormat;


public class ToDo {

    private String id;
    private String text;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dueDate;
    private Boolean done;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime doneDate;
    private int priority; //High, Medium, Low
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime creationDate;

   
    public ToDo(String text, LocalDateTime dueDate, int priority) {
        this.id = UUID.randomUUID().toString().substring(0,8);
        this.text = text;
        this.dueDate = dueDate;
        this.priority = priority;
        this.done = false;
        this.creationDate = LocalDateTime.now();
        this.doneDate = null;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    public LocalDateTime getDoneDate() {
        return doneDate;
    }

    public void setDoneDate(LocalDateTime doneDate) {
        this.doneDate = doneDate;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    
     public static Long getCompletionTime(LocalDateTime creationDate, LocalDateTime doneDate){
        //Java duration class, gets the duration in seconds of the task
        //between the creation and done dates.
        Duration time = Duration.between(creationDate, doneDate);
        return time.getSeconds();
    }  

    public static String formatDate(LocalDateTime date){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
        return dtf.format(date);
    }


    
}
