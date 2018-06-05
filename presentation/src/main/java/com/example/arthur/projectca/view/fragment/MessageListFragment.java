package com.example.arthur.projectca.view.fragment;


import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.arthur.projectca.R;
import com.example.arthur.projectca.internal.di.component.DaggerMessageFragmentComponent;
import com.example.arthur.projectca.internal.di.component.MessageFragmentComponent;
import com.example.arthur.projectca.internal.di.module.MessageModule;
import com.example.arthur.projectca.model.MessageListViewModel;
import com.example.arthur.projectca.presenter.MessageListPresenter;
import com.example.arthur.projectca.view.MessageListView;
import com.example.domain.model.Message;

import javax.inject.Inject;

import butterknife.BindView;


public class MessageListFragment extends BaseFragment implements MessageListView {

    @BindView(R.id.tvTest)
    TextView tvTest;

    @Inject
    MessageListPresenter presenter;

    MessageFragmentComponent component;

    public static MessageListFragment newInstance() {
        return new MessageListFragment();
    }

    public MessageListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_message_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        injectView(this,view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        buildInjection();
        component.inject(this);
        presenter.setView(this);
        initUI();
    }

    private void buildInjection() {
        component = DaggerMessageFragmentComponent.builder()
                .applicationComponent(getAndroidApplication().getApplicationComponent())
                .messageModule(new MessageModule())
                .build();
    }


    @Override
    public void renderList(MessageListViewModel viewModel) {
                StringBuilder messageSB = new StringBuilder();
        for(Message message : viewModel.getMessages()){
            messageSB.append("\n").append(message.getTitle());

        }
        tvTest.setText(messageSB.toString());
    }

    @Override
    public void initUI() {
        presenter.getMessages();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public Context context() {
        return getContext();
    }
}
