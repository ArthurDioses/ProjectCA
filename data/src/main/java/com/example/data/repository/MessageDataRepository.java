package com.example.data.repository;

import com.example.data.network.firebase.DataSnapshotMapper;
import com.example.data.network.firebase.FirebaseDB;
import com.example.data.network.firebase.FirebaseDBImpl;
import com.example.data.network.firebase.References;
import com.example.domain.model.Message;
import com.example.domain.repository.MessageRepository;

import java.util.List;

import rx.Observable;

public class MessageDataRepository implements MessageRepository{
    private final FirebaseDB database;

    public MessageDataRepository() {
        this.database = new FirebaseDBImpl();
    }

    @Override
    public Observable<List<Message>> listMessage() {
        return database.observeValueEvent(References.getMessageReference()).map(DataSnapshotMapper.listOf(Message.class));
    }
}
