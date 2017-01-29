package com.mig35.homeservice.ui.main.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.mig35.homeservice.ui.main.presenter.IControlElementPresenter;

public final class ControlElementView extends LinearLayout implements IControlElementView {

    private IControlElementPresenter mControlElementPresenter;

    public ControlElementView(final Context context) {
        super(context);
    }

    public ControlElementView(final Context context, final AttributeSet attrs) {
        super(context, attrs);
    }

    public ControlElementView(final Context context, final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ControlElementView(final Context context, final AttributeSet attrs, final int defStyleAttr, final int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void init(@NonNull final IControlElementPresenter controlElementPresenter) {
        mControlElementPresenter = controlElementPresenter;
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        if (null != mControlElementPresenter) {
            mControlElementPresenter.bindView(this);
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        if (null != mControlElementPresenter) {
            mControlElementPresenter.unbindView(this);
        }

        super.onDetachedFromWindow();
    }
}