package com.example.arthur.projectca.navigation;

import android.app.Activity;

import com.example.arthur.projectca.view.activity.MessageActivity;


public class Navigator {
    public Navigator() {
    }

    public static void navigateToMessageActivity(Activity activity) {
        activity.startActivity(MessageActivity.getCallingIntent(activity));
    }

}
