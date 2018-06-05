package com.example.arthur.projectca.internal.di.component;

import com.example.arthur.projectca.internal.di.module.MessageModule;
import com.example.arthur.projectca.internal.di.scope.PerFragment;
import com.example.arthur.projectca.view.fragment.MessageListFragment;

import dagger.Component;

@PerFragment
@Component(dependencies = ApplicationComponent.class, modules = MessageModule.class)
public interface MessageFragmentComponent extends FragmentComponent{
    void inject(MessageListFragment fragment);
}
