package com.example.arthur.projectca.presenter;

import com.example.arthur.projectca.internal.di.scope.PerFragment;
import com.example.arthur.projectca.model.MessageMapper;
import com.example.arthur.projectca.view.MessageListView;
import com.example.domain.interactor.GetMessageList;
import com.example.domain.model.Message;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;

@PerFragment
public class MessageListPresenter implements Presenter<MessageListView>{

    private MessageListView view;
    private final GetMessageList useCase;

    @Inject
    MessageListPresenter(GetMessageList useCase) {
        this.useCase = useCase;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        useCase.unsubscribe();
    }

    @Override
    public void setView(MessageListView view) {
        this.view = view;
    }

    public void getMessages()
    {
        useCase.execute(new GetPlanetListSubscriber());
    }

    private class GetPlanetListSubscriber extends Subscriber<List<Message>> {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(List<Message> messages) {
            view.renderList(MessageMapper.transform(messages));
        }
    }
}
