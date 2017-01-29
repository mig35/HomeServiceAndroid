package com.mig35.homeservice.ui.common.base;

import android.support.annotation.CallSuper;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.util.Log;

import com.mig35.homeservice.ui.common.model.BaseScreenModel;
import com.mig35.homeservice.ui.common.view.base.IBaseUserView;
import com.mig35.homeservice.ui.common.view.base.IBaseView;

import java.util.ArrayList;
import java.util.Collection;

public abstract class BasePresenter<View extends IBaseView, Model extends BaseScreenModel> implements IBasePresenter<View> {

    private final Collection<View> mViews = new ArrayList<>(2);

    private final ViewCommandState<View> mViewCommandState = new ViewCommandState<>();

    @NonNull
    protected final Model mModel;

    protected BasePresenter(@NonNull final Model model) {
        mModel = model;
    }

    @MainThread
    @Override
    public final void bindView(@NonNull final View view) {
        mViews.add(view);
        mViewCommandState.apply(view);

        if (1 == mViews.size()) {
            onBindFirstView();
        }
    }

    @MainThread
    @Override
    public final void unbindView(@NonNull final View view) {
        mViews.remove(view);

        if (mViews.isEmpty()) {
            onUnbindLastView();
        }
    }

    @SuppressWarnings("NoopMethodInAbstractClass")
    @CallSuper
    protected void onBindFirstView() {
        // pass
    }

    @SuppressWarnings("NoopMethodInAbstractClass")
    @CallSuper
    protected void onUnbindLastView() {
        // pass
    }

    protected final void executeViewCommandNoHistory(@NonNull final ViewCommand<View> viewCommand) {
        mViewCommandState.addCommandNoHistory(viewCommand, mViews);
    }

    protected final void executeViewCommand(@NonNull final ViewCommand<View> viewCommand) {
        mViewCommandState.addCommand(viewCommand, mViews);
    }

    protected final void executeViewCommandSingle(@NonNull final ViewCommand<View> viewCommand) {
        mViewCommandState.clear();
        executeViewCommand(viewCommand);
    }

    protected final void executeViewCommandSingleAndTerminate(@NonNull final ViewCommand<View> viewCommand) {
        executeViewCommandSingle(viewCommand);
        mViewCommandState.terminate();
    }

    @CallSuper
    protected void doOnError(@NonNull final Throwable throwable) {
        Log.e(getClass().getSimpleName(), "Handle error: doOnError", throwable);
        executeViewCommandNoHistory(view -> doViewOutPut(view, throwable));
    }

    private void doViewOutPut(@NonNull final View view, @NonNull final Throwable throwable) {
        if (view instanceof IBaseUserView) {
            ((IBaseUserView) view).showToast(throwable.getMessage());
        }
    }
}