package com.example.data.exception;

import com.google.firebase.database.DatabaseError;

public class FirebaseDataException extends Exception {
    private DatabaseError error;

    public FirebaseDataException(DatabaseError error) {
        this.error = error;
    }

    public DatabaseError getError() {
        return error;
    }
}
