package com.example.arthur.projectca.model;

import com.example.domain.model.Message;

import java.util.List;

public class MessageMapper {
    public static MessageListViewModel transform(List<Message> messages){
        MessageListViewModel model  = new MessageListViewModel();
        model.setMessages(messages);
        return model;
    }
}
