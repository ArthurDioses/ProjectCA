package com.example.data.exception;

public class FirebaseDataCastExeception extends Exception {
    public FirebaseDataCastExeception() {
    }

    public FirebaseDataCastExeception(String message) {
        super(message);
    }

    public FirebaseDataCastExeception(String message, Throwable cause) {
        super(message, cause);
    }

    public FirebaseDataCastExeception(Throwable cause) {
        super(cause);
    }

}
