package com.mycompany.messagesapp.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString
public class Message {
    @Getter @Setter
    private int id;
    @Getter @Setter
    private String message;
    @Getter @Setter
    private String author;
    @Getter @Setter
    private LocalDateTime messageDate;

    public Message(){
    }
    public Message(String message, String author) {
        this.message = message;
        this.author = author;
    }
}
