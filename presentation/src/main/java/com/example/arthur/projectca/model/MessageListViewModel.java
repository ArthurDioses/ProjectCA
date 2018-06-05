package com.example.arthur.projectca.model;

import com.example.domain.model.Message;

import java.util.List;

public class MessageListViewModel {
    List<Message> messages;

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}
