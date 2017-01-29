package com.mig35.homeservice.ui.common.view.base;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.view.View;

import io.reactivex.disposables.CompositeDisposable;

public abstract class RxBaseFragment extends BaseFragment {

    protected CompositeDisposable mOnCreateSubscription;
    @Nullable
    protected CompositeDisposable mOnViewCreatedSubscription;
    @Nullable
    protected CompositeDisposable mOnResumeSubscription;

    @CallSuper
    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mOnCreateSubscription = new CompositeDisposable();
    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mOnViewCreatedSubscription = new CompositeDisposable();
    }

    @Override
    public void onResume() {
        super.onResume();

        mOnResumeSubscription = new CompositeDisposable();
    }

    @Override
    public void onPause() {
        if (null != mOnResumeSubscription && !mOnResumeSubscription.isDisposed()) {
            mOnResumeSubscription.dispose();
        }
        mOnResumeSubscription = null;

        super.onPause();
    }

    @Override
    public void onDestroyView() {
        if (null != mOnViewCreatedSubscription && !mOnViewCreatedSubscription.isDisposed()) {
            mOnViewCreatedSubscription.dispose();
        }
        mOnViewCreatedSubscription = null;

        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        if (null != mOnCreateSubscription && !mOnCreateSubscription.isDisposed()) {
            mOnCreateSubscription.dispose();
        }

        super.onDestroy();
    }
}