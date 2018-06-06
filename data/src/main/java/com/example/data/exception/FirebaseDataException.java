package com.example.data.exception;

import android.support.annotation.NonNull;

import com.google.firebase.database.DatabaseError;

public class FirebaseDataException extends Exception {

    private DatabaseError error;

    public FirebaseDataException(@NonNull DatabaseError error) {
            this.error = error;
    }

    public DatabaseError getError() {
        return error;
    }
}
