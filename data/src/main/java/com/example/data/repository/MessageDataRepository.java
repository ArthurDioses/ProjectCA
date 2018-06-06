package com.example.data.repository;

import com.example.data.network.firebase.DataSnapshotMapper;
import com.example.data.network.firebase.FirebaseChildEvent;
import com.example.data.network.firebase.FirebaseDB;
import com.example.data.network.firebase.FirebaseDBImpl;
import com.example.data.network.firebase.References;
import com.example.domain.model.Message;
import com.example.domain.repository.MessageRepository;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;
import rx.functions.Func1;

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

    @Override
    public Observable<Message> message() {
        return database.observeChildEvent(FirebaseDatabase.getInstance().getReference().child("messagenodo").child("v1").child("messages")).map(new Func1<FirebaseChildEvent<DataSnapshot>, Message>() {
            @Override
            public Message call(FirebaseChildEvent<DataSnapshot> dataSnapshotFirebaseChildEvent) {
                return DataSnapshotMapper.ofChildEvent(Message.class).call(dataSnapshotFirebaseChildEvent).getValue();
            }
        });
    }

    @Override
    public Observable<Void> sendMessage(String message) {
        return null;
    }
}
