package com.mig35.homeservice.ui.common.base;

import android.support.annotation.MainThread;
import android.support.annotation.NonNull;

import com.mig35.homeservice.ui.common.view.base.IBaseView;

public interface IBasePresenter<T extends IBaseView> {

    @MainThread
    void bindView(@NonNull final T view);

    @MainThread
    void unbindView(@NonNull final T view);
}