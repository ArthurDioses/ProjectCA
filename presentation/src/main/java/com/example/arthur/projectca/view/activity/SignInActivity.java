package com.example.arthur.projectca.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.arthur.projectca.R;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.api.GoogleApiClient;

import butterknife.OnClick;

public class SignInActivity extends AppCompatActivity {

    private static final int REQUEST_AUTH =5555 ;
    private GoogleApiClient mGoogleApiClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
    }

    @OnClick(R.id.sign_in_btn)
    public void btnSignIn()
    {
        Intent intentSignIn = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(intentSignIn,REQUEST_AUTH);
    }
}
