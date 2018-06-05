package com.example.arthur.projectca.view.activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.arthur.projectca.R;

import butterknife.BindView;

public class MessageActivity extends BaseActivity {

    @BindView(R.id.btn_Message)
    Button btnMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        injectView(this);
    }

    public static Intent getCallingIntent(Context context) {
        return new Intent(context,MessageActivity.class);
    }
//    @OnClick(R.id.btn_Message)
//    public  void onMessageListButton(){
//        btnMessage.setSelected(true);
//        Navigator.navigateToPlanetListFragment(getSupportFragmentManager());
//    }
}
