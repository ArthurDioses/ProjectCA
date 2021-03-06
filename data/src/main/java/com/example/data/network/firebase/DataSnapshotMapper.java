package com.example.data.network.firebase;

import com.example.data.exception.FirebaseDataCastExeception;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.GenericTypeIndicator;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import rx.exceptions.Exceptions;
import rx.functions.Func1;

public abstract class DataSnapshotMapper<T, U> implements Func1<T, U> {
    public DataSnapshotMapper() {
    }

    public static <U> DataSnapshotMapper<DataSnapshot, U> of(Class<U> clazz) {
        return new TypedDataSnapshotMapper<>(clazz);
    }

    public static <U> DataSnapshotMapper<DataSnapshot, List<U>> listOf(Class<U> clazz) {
        return new TypedListDataSnapshotMapper<>(clazz);
    }

    public static <U> DataSnapshotMapper<DataSnapshot, LinkedHashMap<String, U>> mapOf(Class<U> clazz) {
        return new TypedMapDataSnapshotMapper<>(clazz);
    }

    public static <U> DataSnapshotMapper<DataSnapshot,U>of(GenericTypeIndicator<U> genericTypeIndicator){
        return new GenericTypeDataSnapshotMapper<>(genericTypeIndicator);
    }

    public static <U> DataSnapshotMapper<FirebaseChildEvent<DataSnapshot>, FirebaseChildEvent<U>> ofChildEvent(Class<U>clazz)
    {
        return new ChildEventDataSnapshotMapper<>(clazz);
    }

    private static <U> U getDataSnapshotTypedValue(DataSnapshot dataSnapshot, Class<U> clazz) {
        U value = dataSnapshot.getValue(clazz);
        if (value == null) {
            throw Exceptions.propagate(new FirebaseDataCastExeception(
                    "unable to cast firebase data response to " + clazz.getSimpleName()));
        }
        return value;
    }

    private static class TypedDataSnapshotMapper<U> extends DataSnapshotMapper<DataSnapshot, U> {
        private final Class<U> clazz;

        private TypedDataSnapshotMapper(Class<U> clazz) {
            this.clazz = clazz;
        }


        @Override
        public U call(DataSnapshot dataSnapshot) {
            if (dataSnapshot.exists()) {
                return getDataSnapshotTypedValue(dataSnapshot, clazz);
            } else {
                return null;
            }
        }

    }

    private static class TypedListDataSnapshotMapper<U> extends DataSnapshotMapper<DataSnapshot, List<U>> {
        private final Class<U> clazz;

        private TypedListDataSnapshotMapper(Class<U> clazz) {
            this.clazz = clazz;
        }

        @Override
        public List<U> call(DataSnapshot dataSnapshot) {
            List<U> items = new ArrayList<>();
            for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                items.add(getDataSnapshotTypedValue(childSnapshot, clazz));
            }
            return items;
        }
    }

    private static class TypedMapDataSnapshotMapper<U> extends DataSnapshotMapper<DataSnapshot, LinkedHashMap<String, U>> {
        private final Class<U> clazz;

        private TypedMapDataSnapshotMapper(Class<U> clazz) {
            this.clazz = clazz;
        }

        @Override
        public LinkedHashMap<String, U> call(DataSnapshot dataSnapshot) {
            LinkedHashMap<String, U> items = new LinkedHashMap<>();
            for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                items.put(childSnapshot.getKey(), getDataSnapshotTypedValue(childSnapshot, clazz));
            }
            return items;
        }
    }

    private static class GenericTypeDataSnapshotMapper<U> extends DataSnapshotMapper<DataSnapshot, U> {
        private final GenericTypeIndicator<U> genericTypeIndicator;

        private GenericTypeDataSnapshotMapper(GenericTypeIndicator<U> genericTypeIndicator) {
            this.genericTypeIndicator = genericTypeIndicator;
        }

        @Override
        public U call(DataSnapshot dataSnapshot) {
            if(dataSnapshot.exists()){
                U value = dataSnapshot.getValue(genericTypeIndicator);
                if(value==null)
                {
                    throw Exceptions.propagate(new FirebaseDataCastExeception(
                       "unable to cast firebase data response to generic type"
                    ));
                }
                return value;
            }
            else {
                return null;
            }
        }
    }

    private static class ChildEventDataSnapshotMapper<U> extends DataSnapshotMapper<FirebaseChildEvent<DataSnapshot>, FirebaseChildEvent<U>> {
        private final Class<U> clazz;

        private ChildEventDataSnapshotMapper(Class<U> clazz) {
            this.clazz = clazz;
        }

        @Override
        public FirebaseChildEvent<U> call(FirebaseChildEvent<DataSnapshot> rxFirebaseChildEvent) {
            DataSnapshot dataSnapshot = rxFirebaseChildEvent.getValue();
            if(dataSnapshot.exists())
            {
                return new FirebaseChildEvent<>(
                        dataSnapshot.getKey(),
                        getDataSnapshotTypedValue(dataSnapshot,clazz),
                        rxFirebaseChildEvent.getPreviousChildName(),
                        rxFirebaseChildEvent.getEventType());
            }
            else
            {
                throw Exceptions.propagate(new RuntimeException("child dataSnapshot doesn't exist"));
            }

        }
    }
}
