package com.mig35.homeservice.ui.common.view.base;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;

import io.reactivex.disposables.CompositeDisposable;

public abstract class RxBaseActivity extends BaseActivity {

    protected CompositeDisposable mOnCreateSubscription;
    @Nullable
    protected CompositeDisposable mOnResumeSubscription;

    @CallSuper
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mOnCreateSubscription = new CompositeDisposable();
    }

    @Override
    protected void onResume() {
        super.onResume();

        mOnResumeSubscription = new CompositeDisposable();
    }

    @Override
    protected void onPause() {
        if (null != mOnResumeSubscription && !mOnResumeSubscription.isDisposed()) {
            mOnResumeSubscription.dispose();
        }
        mOnResumeSubscription = null;

        super.onPause();
    }

    @Override
    protected void onDestroy() {
        if (null != mOnCreateSubscription && !mOnCreateSubscription.isDisposed()) {
            mOnCreateSubscription.dispose();
        }

        super.onDestroy();
    }
}