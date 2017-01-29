package com.mig35.homeservice.utils.rx.general;

import rx.Observable;
import rx.Scheduler;
import rx.Single;

public abstract class RxSchedulersAbs {

    public abstract Scheduler getMainThreadScheduler();

    public abstract Scheduler getIOScheduler();

    public abstract Scheduler getComputationScheduler();

    public <T> Observable.Transformer<T, T> getIOToMainTransformer() {
        return objectObservable -> objectObservable
                .subscribeOn(getIOScheduler())
                .observeOn(getMainThreadScheduler());
    }

    public <T> Single.Transformer<T, T> getIOToMainTransformerSingle() {
        return objectObservable -> objectObservable
                .subscribeOn(getIOScheduler())
                .observeOn(getMainThreadScheduler());
    }

    public <T> Observable.Transformer<T, T> getComputationToMainTransformer() {
        return objectObservable -> objectObservable
                .subscribeOn(getComputationScheduler())
                .observeOn(getMainThreadScheduler());
    }

    public <T> Single.Transformer<T, T> getComputationToMainTransformerSingle() {
        return objectObservable -> objectObservable
                .subscribeOn(getComputationScheduler())
                .observeOn(getMainThreadScheduler());
    }

}
