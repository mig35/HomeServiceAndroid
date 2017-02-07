package com.mig35.homeservice.business.controlelement;

import android.support.annotation.MainThread;
import android.support.annotation.NonNull;

import com.mig35.homeservice.utils.rx.general.RxSchedulersAbs;
import com.mig35.homeservice.utils.rx.observable.ObservableObject;

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

        private final ObservableObject<T> mLastElement;

        CustomObservableOnSubscribe() {
            mLastElement = new ObservableObject<>();
        }

        @Override
        public void subscribe(final ObservableEmitter<T> e) throws Exception {
            final ObservableObject.ElementChangeListener<T> elementChangeListener = e::onNext;

            mLastElement.addElementChangeListener(elementChangeListener);

            e.setCancellable(() -> mLastElement.removeElementChangeListener(elementChangeListener));
        }

        public void notifyNext(final T nextValue) {
            mLastElement.set(nextValue);
        }
    }
}