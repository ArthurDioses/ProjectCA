package com.example.arthur.projectca.internal.di.module;

import android.app.Activity;

import com.example.arthur.projectca.internal.di.scope.PerActivity;
import com.example.domain.interactor.GetMessageList;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

    private final Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    @PerActivity
    Activity provideActivity() {
        return activity;
    }

}
