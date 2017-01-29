package com.mig35.homeservice.dagger.control;

import android.support.annotation.NonNull;

import com.mig35.homeservice.dagger.ActivityScope;
import com.mig35.homeservice.data.ControlElement;
import com.mig35.homeservice.ui.main.model.ControlElementScreenModel;
import com.mig35.homeservice.ui.main.presenter.ControlElementPresenter;
import com.mig35.homeservice.ui.main.presenter.ControlPresenter;
import com.mig35.homeservice.ui.main.presenter.IControlElementPresenter;
import com.mig35.homeservice.ui.main.presenter.IControlPresenter;
import com.mig35.homeservice.utils.rx.general.RxSchedulersAbs;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;

@Module
@ActivityScope
public final class ControlModule {

    @Provides
    @ActivityScope
    public List<IControlElementPresenter> createControlElementsPresenters(@NonNull final ControlElement[] controlElements, @NonNull final RxSchedulersAbs rxSchedulersAbs) {
        final List<IControlElementPresenter> elementPresenters = new ArrayList<>();

        for (final ControlElement controlElement : controlElements) {
            elementPresenters.add(createControlElementPresenter(controlElement, rxSchedulersAbs));
        }

        return elementPresenters;
    }

    @NonNull
    private static IControlElementPresenter createControlElementPresenter(@NonNull final ControlElement controlElement, @NonNull final RxSchedulersAbs rxSchedulersAbs) {
        return new ControlElementPresenter(new ControlElementScreenModel(controlElement.mNameId), rxSchedulersAbs);
    }

    @Provides
    @ActivityScope
    public IControlPresenter createControlPresenter(@NonNull final List<IControlElementPresenter> elementPresenters, @NonNull final RxSchedulersAbs rxSchedulersAbs) {
        return new ControlPresenter(elementPresenters, rxSchedulersAbs);
    }
}