package com.example.domain.interactor;

import com.example.domain.executor.PostExecutionThread;
import com.example.domain.executor.ThreadExecutor;
import com.example.domain.repository.MessageRepository;

import javax.inject.Inject;

import rx.Observable;

public class GetMessageList extends UseCase {

    private final MessageRepository repository;

    @Inject
    protected GetMessageList(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, MessageRepository repository) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return repository.messages();
    }
}
