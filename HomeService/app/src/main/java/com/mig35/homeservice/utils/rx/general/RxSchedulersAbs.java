package com.mig35.homeservice.utils.rx.general;

import io.reactivex.ObservableTransformer;
import io.reactivex.Scheduler;

public abstract class RxSchedulersAbs {

    public abstract Scheduler getMainThreadScheduler();

    public abstract Scheduler getIOScheduler();

    public abstract Scheduler getComputationScheduler();

    public <T> ObservableTransformer<T, T> getIOToMainTransformer() {
        return objectObservable -> objectObservable
                .subscribeOn(getIOScheduler())
                .observeOn(getMainThreadScheduler());
    }

    public <T> ObservableTransformer<T, T> getIOToMainTransformerSingle() {
        return objectObservable -> objectObservable
                .subscribeOn(getIOScheduler())
                .observeOn(getMainThreadScheduler());
    }

    public <T> ObservableTransformer<T, T> getComputationToMainTransformer() {
        return objectObservable -> objectObservable
                .subscribeOn(getComputationScheduler())
                .observeOn(getMainThreadScheduler());
    }

    public <T> ObservableTransformer<T, T> getComputationToMainTransformerSingle() {
        return objectObservable -> objectObservable
                .subscribeOn(getComputationScheduler())
                .observeOn(getMainThreadScheduler());
    }

}
