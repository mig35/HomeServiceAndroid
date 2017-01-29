package com.mig35.homeservice.ui.main.view;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;

import com.mig35.homeservice.R;
import com.mig35.homeservice.dagger.app.AppComponent;
import com.mig35.homeservice.dagger.control.ControlModule;
import com.mig35.homeservice.databinding.ActivityControlBinding;
import com.mig35.homeservice.ui.common.view.base.RxBaseActivity;

public final class ControlActivity extends RxBaseActivity {

    private ActivityControlBinding mDataBinding;

    @Override
    protected void setupBinding() {
        mDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_control);
    }

    @Override
    protected void doInjects(@NonNull final AppComponent appComponent) {
        appComponent.plus(new ControlModule()).inject(this);
    }
}