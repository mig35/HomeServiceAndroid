package com.mig35.homeservice.ui.main.presenter;

import android.support.annotation.NonNull;

import com.mig35.homeservice.ui.common.base.RxBasePresenter;
import com.mig35.homeservice.ui.main.model.ControlElementScreenModel;
import com.mig35.homeservice.ui.main.view.IControlElementView;
import com.mig35.homeservice.utils.rx.general.RxSchedulersAbs;

public final class ControlElementPresenter extends RxBasePresenter<IControlElementView, ControlElementScreenModel> implements IControlElementPresenter {

    public ControlElementPresenter(@NonNull final ControlElementScreenModel model, @NonNull final RxSchedulersAbs rxSchedulersAbs) {
        super(model, rxSchedulersAbs);
    }

    @Override
    public void activate() {
        mModel.mState.set(true);
    }

    @Override
    public void deactivate() {
        mModel.mState.set(false);
    }

    @NonNull
    @Override
    public ControlElementScreenModel getModel() {
        return mModel;
    }
}