package com.example.data.network.firebase;

class FirebaseChildEvent<T> {
    private EventType eventType;
    private String key;
    private T value;
    private String previousChildName;

    public FirebaseChildEvent(String key, T value, String previousChildName,EventType eventType) {
        this.eventType = eventType;
        this.key = key;
        this.value = value;
        this.previousChildName = previousChildName;
    }

    public FirebaseChildEvent(String key, T value,EventType eventType) {
        this.eventType = eventType;
        this.key = key;
        this.value = value;
    }

    public EventType getEventType() {
        return eventType;
    }

    public String getKey() {
        return key;
    }

    public T getValue() {
        return value;
    }

    public String getPreviousChildName() {
        return previousChildName;
    }

    public enum EventType{
        ADDED,
        CHANGED,
        REMOVED,
        MOVED
    }
}
