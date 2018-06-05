package com.example.data.repository;

import com.example.data.network.firebase.DataSnapshotMapper;
import com.example.data.network.firebase.FirebaseDB;
import com.example.data.network.firebase.FirebaseDBImpl;
import com.example.data.network.firebase.References;
import com.example.domain.model.Message;
import com.example.domain.repository.MessageRepository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;

@Singleton
public class MessageDataRepository implements MessageRepository {
    private final FirebaseDB database;

    @Inject
    public MessageDataRepository() {
        this.database = new FirebaseDBImpl();
    }

    @Override
    public Observable<List<Message>> messages() {
        return database.observeValueEvent(References.getMessageReference()).map(DataSnapshotMapper.listOf(Message.class));
    }
}
