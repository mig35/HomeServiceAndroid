package com.mig35.homeservice.ui.main.model;

import android.databinding.ObservableBoolean;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

import com.mig35.homeservice.ui.common.model.BaseScreenModel;

@SuppressWarnings("PublicField")
public final class ControlElementScreenModel extends BaseScreenModel {

    @StringRes
    public final int mNameId;
    @NonNull
    public final ObservableBoolean mState;

    public ControlElementScreenModel(@StringRes final int nameId) {
        mNameId = nameId;
        mState = new ObservableBoolean(false);
    }
}