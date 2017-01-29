package com.mig35.homeservice.utils.binding;

import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import rx.Observable;
import rx.subscriptions.Subscriptions;

public final class BindingObservableUtils {

    private BindingObservableUtils() {
    }

    public static <T> Observable<T> getFieldObservable(@NonNull final ObservableField<T> observableField) {
        return Observable.create(subscriber -> {
            //noinspection AnonymousInnerClass,UnqualifiedInnerClassAccess
            final android.databinding.Observable.OnPropertyChangedCallback propertyChangeCallback = new android.databinding.Observable.OnPropertyChangedCallback() {
                @Override
                public void onPropertyChanged(final android.databinding.Observable observable, final int i) {
                    if (!subscriber.isUnsubscribed()) {
                        subscriber.onNext(observableField.get());
                    }
                }
            };
            subscriber.onNext(observableField.get());

            observableField.addOnPropertyChangedCallback(propertyChangeCallback);
            subscriber.add(Subscriptions.create(() -> observableField.removeOnPropertyChangedCallback(propertyChangeCallback)));
        });
    }
}