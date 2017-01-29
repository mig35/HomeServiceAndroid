package com.mig35.homeservice.utils.general;

import android.support.annotation.NonNull;

import java.util.Stack;

public final class LiFoFixedSizeQueue<T> {

    private final Stack<T> mStack = new Stack<>();
    private final int mMaxSize;

    public LiFoFixedSizeQueue(final int maxSize) {
        mMaxSize = maxSize;
    }

    public void push(@NonNull final T element) {
        if (mStack.size() == mMaxSize) {
            mStack.remove(0);
        }
        mStack.push(element);
    }

    public T pop() {
        return mStack.pop();
    }

    public boolean isEmpty() {
        return mStack.isEmpty();
    }
}