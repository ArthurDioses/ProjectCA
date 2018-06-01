package com.example.arthur.projectca.internal.di.component;

import android.content.Context;

import com.example.arthur.projectca.AndroidApplication;
import com.example.arthur.projectca.internal.di.module.ApplicationModule;
import com.example.domain.executor.PostExecutionThread;
import com.example.domain.executor.ThreadExecutor;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(AndroidApplication application);

    Context context();

    ThreadExecutor threadExecutor();

    PostExecutionThread postExecutionThread();
}
