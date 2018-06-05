package com.example.arthur.projectca.internal.di.component;

import com.example.arthur.projectca.internal.di.module.FragmentModule;
import com.example.arthur.projectca.internal.di.scope.PerFragment;
import com.example.arthur.projectca.view.fragment.BaseFragment;

import dagger.Component;

@PerFragment
@Component(dependencies = ApplicationComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {
    void inject(BaseFragment fragment);
}
