package com.example.domain.repository;


import com.example.domain.model.Message;

import java.util.List;

import rx.Observable;

public interface MessageRepository {
Observable<List<Message>> messages();
}
