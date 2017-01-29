package com.mig35.homeservice.business.controlelement;

import android.support.annotation.MainThread;
import android.support.annotation.NonNull;

import io.reactivex.Observable;

public interface IControlElementInteractor {

    @NonNull
    Observable<Boolean> getStateObservable();

    @MainThread
    void activate();

    @MainThread
    void deactivate();
}