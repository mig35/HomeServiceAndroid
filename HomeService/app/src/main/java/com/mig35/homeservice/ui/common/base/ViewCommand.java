package com.mig35.homeservice.ui.common.base;

import android.support.annotation.NonNull;

import com.mig35.homeservice.ui.common.view.base.IBaseView;

public interface ViewCommand<T extends IBaseView> {

    void execute(@NonNull final T view);
}