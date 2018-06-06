package com.example.data.network.firebase;

import android.support.annotation.NonNull;

public class FirebaseChildEvent<T> {

    private EventType eventType;
    private String key;
    private T value;
    private String previousChildName;

    public FirebaseChildEvent(@NonNull String key,
                              @NonNull T value,
                              @NonNull String previousChildName,
                              @NonNull EventType eventType) {
        this.key = key;
        this.value = value;
        this.previousChildName = previousChildName;
        this.eventType = eventType;
    }


    public FirebaseChildEvent(@NonNull String key, @NonNull T data, @NonNull EventType eventType) {
        this.key = key;
        this.value = data;
        this.eventType = eventType;
    }

    @NonNull
    public String getKey() {
        return key;
    }

    @NonNull
    public T getValue() {
        return value;
    }

    @NonNull
    public String getPreviousChildName() {
        return previousChildName;
    }

    @NonNull
    public EventType getEventType() {
        return eventType;
    }

    public enum EventType {
        ADDED,
        CHANGED,
        REMOVED,
        MOVED
    }
}
