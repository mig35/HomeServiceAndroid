package com.mig35.homeservice.ui.common.view.base;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.view.View;

import rx.subscriptions.CompositeSubscription;

public abstract class RxBaseFragment extends BaseFragment {

    protected CompositeSubscription mOnCreateSubscription;
    @Nullable
    protected CompositeSubscription mOnViewCreatedSubscription;
    @Nullable
    protected CompositeSubscription mOnResumeSubscription;

    @CallSuper
    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mOnCreateSubscription = new CompositeSubscription();
    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mOnViewCreatedSubscription = new CompositeSubscription();
    }

    @Override
    public void onResume() {
        super.onResume();

        mOnResumeSubscription = new CompositeSubscription();
    }

    @Override
    public void onPause() {
        if (null != mOnResumeSubscription && !mOnResumeSubscription.isUnsubscribed()) {
            mOnResumeSubscription.unsubscribe();
        }
        mOnResumeSubscription = null;

        super.onPause();
    }

    @Override
    public void onDestroyView() {
        if (null != mOnViewCreatedSubscription && !mOnViewCreatedSubscription.isUnsubscribed()) {
            mOnViewCreatedSubscription.unsubscribe();
        }
        mOnViewCreatedSubscription = null;

        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        if (null != mOnCreateSubscription && !mOnCreateSubscription.isUnsubscribed()) {
            mOnCreateSubscription.unsubscribe();
        }

        super.onDestroy();
    }
}