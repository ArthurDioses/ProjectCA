package com.example.arthur.projectca.view;

import com.example.arthur.projectca.model.MessageListViewModel;

public interface MessageListView extends BaseView {
    void renderList(MessageListViewModel viewModel);
}
