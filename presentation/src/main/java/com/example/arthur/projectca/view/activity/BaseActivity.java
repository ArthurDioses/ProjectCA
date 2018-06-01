package com.example.arthur.projectca.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.arthur.projectca.AndroidApplication;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected AndroidApplication getAndroidApplication()
    {
        return (AndroidApplication)getApplication();
    }

    protected void injectView(Activity activity){
        ButterKnife.bind(activity);
    }
}
