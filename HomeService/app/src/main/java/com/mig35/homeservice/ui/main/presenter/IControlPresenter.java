package com.mig35.homeservice.ui.main.presenter;

import android.support.annotation.NonNull;

import com.mig35.homeservice.ui.common.base.IBasePresenter;
import com.mig35.homeservice.ui.common.model.UserCommand;
import com.mig35.homeservice.ui.main.view.IControlView;

public interface IControlPresenter extends IBasePresenter<IControlView> {

    void addUserCommand(@NonNull final IControlElementPresenter controlElementPresenter, @NonNull final UserCommand userCommand);

    void undoUserCommand();
}