package com.mig35.homeservice.ui.main.model;

import android.support.annotation.NonNull;

import com.mig35.homeservice.ui.common.model.BaseScreenModel;
import com.mig35.homeservice.ui.main.presenter.IControlElementPresenter;

import java.util.Collections;
import java.util.List;

@SuppressWarnings("PublicField")
public final class ControlScreenModel extends BaseScreenModel {

    @NonNull
    private final List<IControlElementPresenter> mElementPresenters;

    public ControlScreenModel(@NonNull final List<IControlElementPresenter> elementPresenters) {
        mElementPresenters = Collections.unmodifiableList(elementPresenters);
    }

    @NonNull
    public List<IControlElementPresenter> getElementPresenters() {
        //noinspection ReturnOfCollectionOrArrayField
        return mElementPresenters;
    }
}