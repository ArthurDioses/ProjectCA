package com.example.arthur.projectca.view.activity;

import android.os.Bundle;

import com.example.arthur.projectca.R;
import com.example.arthur.projectca.internal.di.component.ActivityComponent;
import com.example.arthur.projectca.internal.di.component.DaggerActivityComponent;
import com.example.arthur.projectca.internal.di.module.ActivityModule;
import com.example.arthur.projectca.navigation.Navigator;


import butterknife.OnClick;

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
    @OnClick(R.id.btn_next_activity)
    public void btnNextActivity()
    {
        Navigator.navigateToMessageActivity(this);
    }
}
