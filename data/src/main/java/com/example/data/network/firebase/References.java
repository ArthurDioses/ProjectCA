package com.example.data.network.firebase;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class References {
    private static final String ROOT ="";
    private static final String VERSION ="";
    private static final String MESSAGE ="";

    public static DatabaseReference getMessageReference()
    {
        return FirebaseDatabase.getInstance().getReference(ROOT).child(VERSION).child(MESSAGE);
    }
}
