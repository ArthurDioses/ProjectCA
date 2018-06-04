package com.example.data.network.firebase;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.Query;

import rx.Observable;

public interface FirebaseDB {
    Observable<DataSnapshot> observeValueEvent(Query query);
    Observable<DataSnapshot> observeSingleValueEvent(Query query);
    Observable<FirebaseChildEvent<DataSnapshot>> observeChildEvent(Query query);
}
