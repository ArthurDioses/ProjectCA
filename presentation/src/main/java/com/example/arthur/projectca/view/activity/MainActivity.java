package com.example.arthur.projectca.view.activity;

import android.os.Bundle;

import com.example.arthur.projectca.R;
import com.example.arthur.projectca.internal.di.component.ActivityComponent;
import com.example.arthur.projectca.internal.di.component.DaggerActivityComponent;
import com.example.arthur.projectca.internal.di.module.ActivityModule;

public class MainActivity extends BaseActivity {

    ActivityComponent component;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        injectView(this);
        buildInject();
        component.inject(this);
    }

    private void buildInject() {
        component = DaggerActivityComponent.builder()
                .applicationComponent(getAndroidApplication().getApplicationComponent())
                .activityModule(new ActivityModule(this))
                .build();
    }
}
