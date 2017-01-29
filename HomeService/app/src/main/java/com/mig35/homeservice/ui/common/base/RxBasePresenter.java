package com.mig35.homeservice.ui.common.base;

import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.mig35.homeservice.ui.common.model.BaseScreenModel;
import com.mig35.homeservice.ui.common.view.base.IBaseView;
import com.mig35.homeservice.utils.rx.general.RxSchedulersAbs;

import io.reactivex.disposables.CompositeDisposable;

public abstract class RxBasePresenter<View extends IBaseView, Model extends BaseScreenModel> extends BasePresenter<View, Model> {

    @NonNull
    protected final RxSchedulersAbs mRxSchedulersAbs;

    @Nullable
    private CompositeDisposable mSubscription;

    protected RxBasePresenter(@NonNull final Model model, @NonNull final RxSchedulersAbs rxSchedulersAbs) {
        super(model);

        mRxSchedulersAbs = rxSchedulersAbs;
    }

    @CallSuper
    @Override
    protected void onBindFirstView() {
        super.onBindFirstView();

        mSubscription = new CompositeDisposable();
        onBindFirstView(mSubscription);
    }

    @SuppressWarnings("NoopMethodInAbstractClass")
    @CallSuper
    protected void onBindFirstView(@NonNull final CompositeDisposable subscription) {
        // pass
    }

    @Override
    @CallSuper
    protected void onUnbindLastView() {
        if (null != mSubscription) {
            onUnbindLastView(mSubscription);

            mSubscription.dispose();
            mSubscription = null;
        }

        super.onUnbindLastView();
    }

    @SuppressWarnings("NoopMethodInAbstractClass")
    @CallSuper
    protected void onUnbindLastView(@NonNull final CompositeDisposable subscription) {
        // pass
    }
}