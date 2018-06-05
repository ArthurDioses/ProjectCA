package com.example.arthur.projectca.internal.di.module;

import com.example.arthur.projectca.internal.di.scope.PerFragment;
import com.example.domain.interactor.GetMessageList;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class MessageModule {


    @Provides
    @PerFragment
    @Named("getMessageList")
    GetMessageList provideGetMessageUseCase(GetMessageList messageList){
        return messageList;
    }

}
