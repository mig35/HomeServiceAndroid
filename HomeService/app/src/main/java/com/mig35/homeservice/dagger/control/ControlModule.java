package com.mig35.homeservice.dagger.control;

import android.support.annotation.NonNull;

import com.mig35.homeservice.business.control.ControlInteractor;
import com.mig35.homeservice.business.control.IControlInteractor;
import com.mig35.homeservice.business.controlelement.ControlElementsBuilder;
import com.mig35.homeservice.business.controlelement.IControlElementInteractor;
import com.mig35.homeservice.business.controlelement.IControlElementsBuilder;
import com.mig35.homeservice.business.controlelement.aircondition.IAirConditionControlElementInteractor;
import com.mig35.homeservice.business.controlelement.light.ILightControlElementInteractor;
import com.mig35.homeservice.business.controlelement.stereo.IStereoControlElementInteractor;
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
    public IControlElementsBuilder provideControlElementsBuilder(@NonNull final IStereoControlElementInteractor stereoControlElementInteractor, @NonNull final ILightControlElementInteractor lightElementInteractor, @NonNull final IAirConditionControlElementInteractor airConditionElementInteractor) {
        return new ControlElementsBuilder(stereoControlElementInteractor, lightElementInteractor, airConditionElementInteractor);
    }

    @Provides
    @ActivityScope
    public IControlInteractor provideControlInteractor() {
        return new ControlInteractor();
    }

    @Provides
    @ActivityScope
    public List<IControlElementPresenter> provideControlElementsPresenters(@NonNull final IControlElementsBuilder controlElementsBuilder, @NonNull final ControlElement[] controlElements, @NonNull final RxSchedulersAbs rxSchedulersAbs) {
        final List<IControlElementPresenter> elementPresenters = new ArrayList<>();

        for (final ControlElement controlElement : controlElements) {
            final IControlElementInteractor controlElementInteractor = controlElementsBuilder.createInteractor(controlElement);
            //noinspection ObjectAllocationInLoop
            final ControlElementPresenter controlElementPresenter = new ControlElementPresenter(new ControlElementScreenModel(controlElement.mNameId), controlElementInteractor, rxSchedulersAbs);
            elementPresenters.add(controlElementPresenter);
        }

        return elementPresenters;
    }

    @Provides
    @ActivityScope
    public IControlPresenter provideControlPresenter(@NonNull final List<IControlElementPresenter> elementPresenters, @NonNull final IControlInteractor controlInteractor, @NonNull final RxSchedulersAbs rxSchedulersAbs) {
        return new ControlPresenter(elementPresenters, controlInteractor, rxSchedulersAbs);
    }
}