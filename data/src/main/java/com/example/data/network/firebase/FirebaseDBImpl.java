package com.example.data.network.firebase;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.data.exception.FirebaseDataException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action0;
import rx.subscriptions.Subscriptions;

public class FirebaseDBImpl implements FirebaseDB {
    private Context context;

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

    private boolean isSubscribed(Subscriber subscriber, Query query, ValueEventListener listener) {
        if (subscriber.isUnsubscribed()) {
            query.removeEventListener(listener);
            return false;
        }
        return true;
    }
}