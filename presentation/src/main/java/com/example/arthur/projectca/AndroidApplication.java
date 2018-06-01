package com.example.arthur.projectca;

import android.app.Application;

import com.example.arthur.projectca.internal.di.component.ApplicationComponent;
import com.example.arthur.projectca.internal.di.component.DaggerApplicationComponent;
import com.example.arthur.projectca.internal.di.module.ApplicationModule;

public class AndroidApplication extends Application {
    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        buildInjection();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

    private void buildInjection() {
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        applicationComponent.inject(this);
    }

}
