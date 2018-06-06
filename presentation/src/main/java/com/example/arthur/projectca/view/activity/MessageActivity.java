package com.example.arthur.projectca.view.activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.arthur.projectca.R;
import com.example.arthur.projectca.navigation.Navigator;

import butterknife.BindView;
import butterknife.OnClick;

public class MessageActivity extends BaseActivity {

//    @BindView(R.id.btn_Message)
//    Button btnMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        injectView(this);
        Navigator.navigateToMessageListFragment(getSupportFragmentManager());
    }

    public static Intent getCallingIntent(Context context) {
        return new Intent(context,MessageActivity.class);
    }
//    @OnClick(R.id.btn_Message)
//    public  void onMessageListButton(){
//        btnMessage.setSelected(true);
//        Navigator.navigateToMessageListFragment(getSupportFragmentManager());
//    }
}
