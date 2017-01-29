package com.mig35.homeservice.business.controlelement;

import android.support.annotation.MainThread;
import android.support.annotation.NonNull;

import com.mig35.homeservice.utils.general.Guard;
import com.mig35.homeservice.utils.rx.general.RxSchedulersAbs;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

public abstract class BaseControlElementInteractor implements IControlElementInteractor {

    @NonNull
    private final CustomObservableOnSubscribe<Boolean> mActivateDeactivateOnSubscribe;
    @NonNull
    private final Observable<Boolean> mStateObservable;

    protected BaseControlElementInteractor(@NonNull final RxSchedulersAbs rxSchedulersAbs) {
        mActivateDeactivateOnSubscribe = new CustomObservableOnSubscribe<>();
        final CustomObservableOnSubscribe<Boolean> stateOnSubscribe = new CustomObservableOnSubscribe<>();

        Observable.create(mActivateDeactivateOnSubscribe)
                .subscribeOn(rxSchedulersAbs.getMainThreadScheduler())
                .distinctUntilChanged()
                .observeOn(rxSchedulersAbs.getComputationScheduler())
                .doOnNext(aBoolean -> {
                    if (aBoolean) {
                        activateInner();
                    } else {
                        deactivateInner();
                    }
                })
                .observeOn(rxSchedulersAbs.getMainThreadScheduler())
                .subscribe(stateOnSubscribe::notifyNext);

        mStateObservable = Observable.create(stateOnSubscribe)
                .subscribeOn(rxSchedulersAbs.getMainThreadScheduler())
                .distinctUntilChanged()
                .cacheWithInitialCapacity(1);
    }

    @NonNull
    @Override
    public Observable<Boolean> getStateObservable() {
        return mStateObservable;
    }

    @MainThread
    @Override
    public final void activate() {
        mActivateDeactivateOnSubscribe.notifyNext(true);
    }

    @MainThread
    @Override
    public final void deactivate() {
        mActivateDeactivateOnSubscribe.notifyNext(false);
    }

    protected abstract void activateInner();

    protected abstract void deactivateInner();

    @MainThread
    private static final class CustomObservableOnSubscribe<T> implements ObservableOnSubscribe<T> {

        private final List<ObservableEmitter<T>> mObservableEmitters;

        CustomObservableOnSubscribe() {
            mObservableEmitters = new ArrayList<>();
        }

        @Override
        public void subscribe(final ObservableEmitter<T> e) throws Exception {
            mObservableEmitters.add(e);

            e.setCancellable(() -> {
                if (Guard.isUiThread()) {
                    mObservableEmitters.remove(e);
                }
            });
        }

        public void notifyNext(final T nextValue) {
            final Iterator<ObservableEmitter<T>> iterator = mObservableEmitters.iterator();
            while (iterator.hasNext()) {
                final ObservableEmitter<T> item = iterator.next();
                if (item.isDisposed()) {
                    iterator.remove();
                } else {
                    item.onNext(nextValue);
                }
            }
        }
    }
}