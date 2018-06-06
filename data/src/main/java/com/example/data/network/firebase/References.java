package com.example.data.network.firebase;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class References {
//    private static final String ROOT ="projectca-7eebf";
    private static final String NODO ="messagenodo";
    private static final String VERSION ="v1";
    private static final String MESSAGE ="messages";

    public static DatabaseReference getMessageReference()
    {
        return FirebaseDatabase.getInstance().getReference().child(NODO).child(VERSION).child(MESSAGE);
    }
}
