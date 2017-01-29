package com.mig35.homeservice.ui.common.view.base;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.azoft.injectorlib.InjectSavedState;
import com.azoft.injectorlib.Injector;
import com.mig35.homeservice.app.HomeServiceApplication;
import com.mig35.homeservice.dagger.app.AppComponent;
import com.mig35.homeservice.ui.common.view.DaggerHelper;
import com.mig35.homeservice.utils.android.ToastShower;

import javax.inject.Inject;

public abstract class BaseActivity extends AppCompatActivity implements IBaseUserView {

    private final Injector mInjector = Injector.init(getClass());

    @InjectSavedState
    private DaggerHelper mDaggerHelper;

    @Inject
    ToastShower mToastShower;

    @CallSuper
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mInjector.applyRestoreInstanceState(this, savedInstanceState);

        if (null == mDaggerHelper) {
            mDaggerHelper = new DaggerHelper();
        }
        setupDaggerInjects();
        setupBinding();
    }

    @Override
    protected void onPostCreate(@Nullable final Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        mDaggerHelper.bindView(this);
    }

    @CallSuper
    protected void setupDaggerInjects() {
        mDaggerHelper.onCreate(this, AppCompatActivity.class, () -> doInjects(((HomeServiceApplication) getApplication()).getAppComponent()));
    }

    protected abstract void setupBinding();

    protected abstract void doInjects(@NonNull final AppComponent appComponent);

    public HomeServiceApplication getHomeServiceApplication() {
        return (HomeServiceApplication) getApplication();
    }

    @CallSuper
    @Override
    protected void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);

        mInjector.applyOnSaveInstanceState(this, outState);
    }

    @CallSuper
    @Override
    protected void onDestroy() {
        mDaggerHelper.unbindView(this);
        mDaggerHelper.onDestroy(isFinishing());

        super.onDestroy();
    }

    @Override
    public void showToast(@NonNull final String message) {
        mToastShower.showToast(message);
    }
}