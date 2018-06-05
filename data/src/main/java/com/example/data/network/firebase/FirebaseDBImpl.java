package com.example.data.network.firebase;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.data.exception.FirebaseDataException;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action0;
import rx.subscriptions.Subscriptions;

public class FirebaseDBImpl implements FirebaseDB {

    @Override
    public Observable<DataSnapshot> observeValueEvent(final Query query) {
        return Observable.create(new Observable.OnSubscribe<DataSnapshot>() {
            @Override
            public void call(final Subscriber<? super DataSnapshot> subscriber) {
                final ValueEventListener valueEventListener = query.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (isSubscribed(subscriber, query, this)) {
                            subscriber.onNext(dataSnapshot);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        if (isSubscribed(subscriber, query, this)) {
                            subscriber.onError(new FirebaseDataException(databaseError));
                        }
                    }
                });
                subscriber.add(Subscriptions.create(new Action0() {
                    @Override
                    public void call() {
                        query.removeEventListener(valueEventListener);
                    }
                }));
            }
        });
    }

    @Override
    public Observable<DataSnapshot> observeSingleValueEvent(final Query query) {
        return Observable.create(new Observable.OnSubscribe<DataSnapshot>() {
            @Override
            public void call(final Subscriber<? super DataSnapshot> subscriber) {
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(isSubscribed(subscriber,query,this))
                        {
                            subscriber.onCompleted();
                            subscriber.onNext(dataSnapshot);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        if(isSubscribed(subscriber,query,this)){
                            subscriber.onError(new FirebaseDataException(databaseError));
                        }
                    }
                });
            }
        });
    }

    @Override
    public Observable<FirebaseChildEvent<DataSnapshot>> observeChildEvent(final Query query) {
        return Observable.create(new Observable.OnSubscribe<FirebaseChildEvent<DataSnapshot>>() {
            @Override
            public void call(final Subscriber<? super FirebaseChildEvent<DataSnapshot>> subscriber) {
                final ChildEventListener childEventListener = query.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String previousChildName) {
                        if(isSubscribed(subscriber,query,this))
                        {
                            subscriber.onNext(new FirebaseChildEvent<DataSnapshot>(dataSnapshot.getKey(),
                                    dataSnapshot, previousChildName,FirebaseChildEvent.EventType.ADDED));
                        }
                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String previousChildName) {
                        if(isSubscribed(subscriber,query,this)){
                            subscriber.onNext(new FirebaseChildEvent<DataSnapshot>(dataSnapshot.getKey(),
                                    dataSnapshot,previousChildName, FirebaseChildEvent.EventType.CHANGED));
                        }
                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                        if(isSubscribed(subscriber,query,this)){
                            subscriber.onNext(new FirebaseChildEvent<DataSnapshot>(dataSnapshot.getKey(),
                                    dataSnapshot,FirebaseChildEvent.EventType.REMOVED));
                        }
                    }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String previousChildName) {
                        if(isSubscribed(subscriber,query,this)){
                            subscriber.onNext(new FirebaseChildEvent<DataSnapshot>(dataSnapshot.getKey(),
                                    dataSnapshot,previousChildName,FirebaseChildEvent.EventType.MOVED));
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        if(isSubscribed(subscriber,query,this)){
                            subscriber.onError(new FirebaseDataException(databaseError));
                        }
                    }
                });
                subscriber.add(Subscriptions.create(new Action0() {
                    @Override
                    public void call() {
                        query.removeEventListener(childEventListener);
                    }
                }));
            }
        });
    }

    private boolean isSubscribed(Subscriber subscriber, Query query, ValueEventListener listener) {
        if (subscriber.isUnsubscribed()) {
            query.removeEventListener(listener);
            return false;
        }
        return true;
    }

    private boolean isSubscribed(Subscriber subscriber, Query query, ChildEventListener listener) {
        if (subscriber.isUnsubscribed()) {
            query.addChildEventListener(listener);
            return false;
        }
        return true;
    }
}