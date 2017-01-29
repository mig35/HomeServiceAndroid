package com.mig35.homeservice.ui.main.presenter;

import android.support.annotation.NonNull;

import com.mig35.homeservice.business.controlelement.IControlElementInteractor;
import com.mig35.homeservice.ui.common.base.RxBasePresenter;
import com.mig35.homeservice.ui.common.model.UserCommand;
import com.mig35.homeservice.ui.main.model.ControlElementScreenModel;
import com.mig35.homeservice.ui.main.view.IControlElementView;
import com.mig35.homeservice.utils.rx.general.RxSchedulersAbs;

import io.reactivex.disposables.CompositeDisposable;

public final class ControlElementPresenter extends RxBasePresenter<IControlElementView, ControlElementScreenModel> implements IControlElementPresenter {

    @NonNull
    private final IControlElementInteractor mInteractor;

    public ControlElementPresenter(@NonNull final ControlElementScreenModel model, @NonNull final IControlElementInteractor controlElementInteractor, @NonNull final RxSchedulersAbs rxSchedulersAbs) {
        super(model, rxSchedulersAbs);

        mInteractor = controlElementInteractor;
    }

    @Override
    protected void onBindFirstView(@NonNull final CompositeDisposable subscription) {
        super.onBindFirstView(subscription);

        subscription.add(
                mInteractor.getStateObservable()
                        .compose(mRxSchedulersAbs.getComputationToMainTransformer())
                        .subscribe(mModel.mState::set)
        );
    }

    @Override
    public void sendUserCommand(@NonNull final UserCommand userCommand) {
        switch (userCommand) {
            case ON:
                mInteractor.activate();
                break;
            case OFF:
                mInteractor.deactivate();
                break;
            default:
                throw new IllegalArgumentException("unknown user command: " + userCommand);
        }
    }

    @NonNull
    @Override
    public ControlElementScreenModel getModel() {
        return mModel;
    }
}