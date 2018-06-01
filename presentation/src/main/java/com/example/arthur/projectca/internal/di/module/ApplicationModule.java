package com.example.arthur.projectca.internal.di.module;

import android.content.Context;

import com.example.arthur.projectca.AndroidApplication;
import com.example.arthur.projectca.UIThread;
import com.example.data.executor.JobExecutor;
import com.example.domain.executor.PostExecutionThread;
import com.example.domain.executor.ThreadExecutor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {
    private final AndroidApplication application;

    public ApplicationModule(AndroidApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    AndroidApplication provideApplication(){
        return application;
    }

    @Provides
    @Singleton
    Context provideContext(){
        return application.getApplicationContext();
    }

    @Provides
    @Singleton
    ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor)
    {
        return jobExecutor;
    }

    @Provides
    @Singleton
    PostExecutionThread providePostExecutionThread(UIThread uiThread)
    {
        return uiThread;
    }

}
