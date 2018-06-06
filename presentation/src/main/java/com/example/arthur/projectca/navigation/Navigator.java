package com.example.arthur.projectca.navigation;


import android.app.Activity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.arthur.projectca.R;
import com.example.arthur.projectca.view.activity.MessageActivity;
import com.example.arthur.projectca.view.fragment.MessageListFragment;

public class Navigator {
    public Navigator() {
    }

    public static void navigateToMessageActivity(Activity activity) {
        activity.startActivity(MessageActivity.getCallingIntent(activity));
    }

    public static void navigateToMessageListFragment(FragmentManager supportFragmentManager) {
        FragmentTransaction transaction = supportFragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container, MessageListFragment.newInstance(),MessageListFragment.class.getSimpleName());
        transaction.commit();
    }
}
