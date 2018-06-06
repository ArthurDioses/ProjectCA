package com.example.data.exception;

import android.support.annotation.NonNull;

public class FirebaseDataCastException extends Exception {

    public FirebaseDataCastException() {
    }

    public FirebaseDataCastException(@NonNull String detailMessage) {
        super(detailMessage);
    }

    public FirebaseDataCastException(@NonNull Throwable throwable) {
        super(throwable);
    }

    public FirebaseDataCastException(@NonNull String detailMessage, @NonNull Throwable throwable) {
        super(detailMessage, throwable);
    }
}
