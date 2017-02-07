package com.mig35.homeservice.utils.rx.observable;

import android.support.annotation.NonNull;

import com.mig35.homeservice.utils.general.GeneralUtils;

import java.util.ArrayList;
import java.util.List;

public final class ObservableObject<T> {

    private final List<ElementChangeListener<T>> mChangeListeners = new ArrayList<>();
    private final Object mSyncObject = new Object();
    private T mElement;

    public void set(final T nextValue) {
        synchronized (mSyncObject) {
            if (!GeneralUtils.equals(nextValue, mElement)) {
                mElement = nextValue;
                notifyChange();
            }
        }
    }

    public void addElementChangeListener(final ElementChangeListener<T> elementChangeListener) {
        synchronized (mSyncObject) {
            mChangeListeners.add(elementChangeListener);
        }
    }

    public void removeElementChangeListener(final ElementChangeListener<T> elementChangeListener) {
        synchronized (mSyncObject) {
            mChangeListeners.remove(elementChangeListener);
        }
    }

    private void notifyChange() {
        for (final ElementChangeListener<T> elementChangeListener : mChangeListeners) {
            elementChangeListener.onElementChanged(mElement);
        }
    }

    public interface ElementChangeListener<T> {

        void onElementChanged(@NonNull final T element);
    }
}