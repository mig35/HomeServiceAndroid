package com.mig35.homeservice.ui.main.view;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;

import com.mig35.homeservice.R;
import com.mig35.homeservice.dagger.app.AppComponent;
import com.mig35.homeservice.dagger.control.ControlModule;
import com.mig35.homeservice.databinding.ActivityControlBinding;
import com.mig35.homeservice.databinding.ItemControlElementBinding;
import com.mig35.homeservice.ui.common.view.base.RxBaseActivity;
import com.mig35.homeservice.ui.main.model.ControlScreenModel;
import com.mig35.homeservice.ui.main.presenter.IControlElementPresenter;
import com.mig35.homeservice.ui.main.presenter.IControlPresenter;

import javax.inject.Inject;

public final class ControlActivity extends RxBaseActivity implements IControlView {

    private ActivityControlBinding mDataBinding;

    @Inject
    IControlPresenter mControlPresenter;

    @Override
    protected void setupBinding() {
        mDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_control);
    }

    @Override
    protected void doInjects(@NonNull final AppComponent appComponent) {
        appComponent.plus(new ControlModule()).inject(this);
    }

    @Override
    public void updateScreenModel(final ControlScreenModel controlScreenModel) {
        mDataBinding.controlsContainer.removeAllViews();

        for (final IControlElementPresenter controlElementPresenter : controlScreenModel.getElementPresenters()) {
            final ItemControlElementBinding elementViewBinding = DataBindingUtil
                    .inflate(getLayoutInflater(), R.layout.item_control_element, mDataBinding.controlsContainer, false);

            elementViewBinding.setScreenModel(controlElementPresenter.getModel());
            elementViewBinding.onButton.setOnClickListener(v -> controlElementPresenter.activate());
            elementViewBinding.offButton.setOnClickListener(v -> controlElementPresenter.deactivate());

            mDataBinding.controlsContainer.addView(elementViewBinding.getRoot());
        }
    }
}