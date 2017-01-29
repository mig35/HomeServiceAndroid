package com.mig35.homeservice.ui.common.view.base;

import android.app.Fragment;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.azoft.injectorlib.InjectSavedState;
import com.azoft.injectorlib.Injector;
import com.mig35.homeservice.dagger.app.AppComponent;
import com.mig35.homeservice.ui.common.view.DaggerHelper;
import com.squareup.leakcanary.RefWatcher;

import javax.inject.Inject;

public abstract class BaseFragment extends Fragment implements IBaseView {

    private final Injector mInjector = Injector.init(getClass());

    @InjectSavedState
    private DaggerHelper mDaggerHelper;

    @Inject
    RefWatcher mRefWatcher;

    @CallSuper
    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mInjector.applyRestoreInstanceState(this, savedInstanceState);

        if (null == mDaggerHelper) {
            mDaggerHelper = new DaggerHelper();
        }
        mDaggerHelper.onCreate(this, BaseFragment.class, () -> doInjects(getBaseActivity().getHomeServiceApplication().getAppComponent()));
    }

    @Nullable
    @Override
    public final View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        final ViewDataBinding binding = setupBinding(inflater, container);
        if (null == binding) {
            return null;
        }
        return binding.getRoot();
    }

    @Override
    public void onViewStateRestored(final Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

        mDaggerHelper.bindView(this);
    }

    @Nullable
    protected abstract ViewDataBinding setupBinding(@NonNull final LayoutInflater inflater, @NonNull final ViewGroup container);

    protected abstract void doInjects(@NonNull final AppComponent appComponent);

    protected BaseActivity getBaseActivity() {
        return (BaseActivity) getActivity();
    }

    @CallSuper
    @Override
    public void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);

        mInjector.applyOnSaveInstanceState(this, outState);
    }

    @Override
    public void onDestroyView() {
        mDaggerHelper.unbindView(this);

        super.onDestroyView();
    }

    @CallSuper
    @Override
    public void onDestroy() {
        super.onDestroy();

        final boolean activityIsFinishing = getActivity().isFinishing() || isRemoving();
        mDaggerHelper.onDestroy(activityIsFinishing);
        if (activityIsFinishing && null != mRefWatcher) {
            mRefWatcher.watch(this);
        }
    }
}
