package com.mig35.homeservice.ui.common.view.base;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;

import rx.subscriptions.CompositeSubscription;

public abstract class RxBaseActivity extends BaseActivity {

    protected CompositeSubscription mOnCreateSubscription;
    @Nullable
    protected CompositeSubscription mOnResumeSubscription;

    @CallSuper
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mOnCreateSubscription = new CompositeSubscription();
    }

    @Override
    protected void onResume() {
        super.onResume();

        mOnResumeSubscription = new CompositeSubscription();
    }

    @Override
    protected void onPause() {
        if (null != mOnResumeSubscription && !mOnResumeSubscription.isUnsubscribed()) {
            mOnResumeSubscription.unsubscribe();
        }
        mOnResumeSubscription = null;

        super.onPause();
    }

    @Override
    protected void onDestroy() {
        if (null != mOnCreateSubscription && !mOnCreateSubscription.isUnsubscribed()) {
            mOnCreateSubscription.unsubscribe();
        }

        super.onDestroy();
    }
}