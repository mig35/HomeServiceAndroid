package com.mig35.homeservice.ui.main.presenter;

import android.support.annotation.NonNull;

import com.mig35.homeservice.ui.common.base.RxBasePresenter;
import com.mig35.homeservice.ui.main.model.ControlScreenModel;
import com.mig35.homeservice.ui.main.view.IControlView;
import com.mig35.homeservice.utils.rx.general.RxSchedulersAbs;

import java.util.List;

public final class ControlPresenter extends RxBasePresenter<IControlView, ControlScreenModel> implements IControlPresenter {

    @NonNull
    private final List<IControlElementPresenter> mControlElementPresenters;

    public ControlPresenter(@NonNull final List<IControlElementPresenter> elementPresenters, @NonNull final RxSchedulersAbs rxSchedulersAbs) {
        super(new ControlScreenModel(elementPresenters), rxSchedulersAbs);

        mControlElementPresenters = elementPresenters;

        executeViewCommandSingle(view -> view.updateScreenModel(mModel));
    }
}