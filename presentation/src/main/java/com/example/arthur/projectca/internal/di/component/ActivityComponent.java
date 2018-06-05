package com.example.arthur.projectca.internal.di.component;

import android.app.Activity;

import com.example.arthur.projectca.internal.di.module.ActivityModule;
import com.example.arthur.projectca.internal.di.scope.PerActivity;
import com.example.arthur.projectca.view.activity.MainActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity activity);

}
