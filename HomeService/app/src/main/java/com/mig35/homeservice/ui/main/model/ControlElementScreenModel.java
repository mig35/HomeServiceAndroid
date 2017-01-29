package com.mig35.homeservice.ui.main.model;

import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

import com.mig35.homeservice.ui.common.model.BaseScreenModel;

@SuppressWarnings("PublicField")
public final class ControlElementScreenModel extends BaseScreenModel {

    @StringRes
    public final int mNameId;
    @NonNull
    public final ObservableField<Boolean> mState;

    public ControlElementScreenModel(@StringRes final int nameId) {
        mNameId = nameId;
        mState = new ObservableField<>(false);
    }
}