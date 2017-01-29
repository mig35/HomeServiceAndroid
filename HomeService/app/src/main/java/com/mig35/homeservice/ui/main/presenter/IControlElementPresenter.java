package com.mig35.homeservice.ui.main.presenter;

import android.support.annotation.NonNull;

import com.mig35.homeservice.ui.common.base.IBasePresenter;
import com.mig35.homeservice.ui.common.model.UserCommand;
import com.mig35.homeservice.ui.main.model.ControlElementScreenModel;
import com.mig35.homeservice.ui.main.view.IControlElementView;

public interface IControlElementPresenter extends IBasePresenter<IControlElementView> {

    void sendUserCommand(@NonNull final UserCommand userCommand);

    @NonNull
    ControlElementScreenModel getModel();
}