package com.mig35.homeservice.utils.android;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.Toast;

public final class ToastShower {

    @NonNull
    private final Context mContext;

    private Toast mToast;

    public ToastShower(@NonNull final Context context) {
        mContext = context;
    }

    public void showToast(@NonNull final String message) {
        if (null != mToast) {
            mToast.cancel();
        }
        mToast = Toast.makeText(mContext, message, Toast.LENGTH_SHORT);
        mToast.show();
    }
}