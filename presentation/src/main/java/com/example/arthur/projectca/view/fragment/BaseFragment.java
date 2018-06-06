package com.example.arthur.projectca.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.example.arthur.projectca.AndroidApplication;

import butterknife.ButterKnife;

public abstract class BaseFragment extends Fragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected AndroidApplication getAndroidApplication() {
        return (AndroidApplication) getActivity().getApplication();
    }

    protected void injectView(Fragment fragment, View view) {
        ButterKnife.bind(fragment, view);
    }
}
