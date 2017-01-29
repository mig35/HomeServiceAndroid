package com.mig35.homeservice.dagger.control;

import android.support.annotation.NonNull;

import com.mig35.homeservice.business.controlelement.aircondition.AirConditionControlElementInteractor;
import com.mig35.homeservice.business.controlelement.aircondition.IAirConditionControlElementInteractor;
import com.mig35.homeservice.business.controlelement.light.ILightControlElementInteractor;
import com.mig35.homeservice.business.controlelement.light.LightControlElementInteractor;
import com.mig35.homeservice.business.controlelement.stereo.IStereoControlElementInteractor;
import com.mig35.homeservice.business.controlelement.stereo.StereoControlElementInteractor;
import com.mig35.homeservice.business.system.aircondition.IAirConditionSystemElement;
import com.mig35.homeservice.business.system.light.ILightSystemElement;
import com.mig35.homeservice.business.system.stereo.IStereoSystemElement;
import com.mig35.homeservice.dagger.ActivityScope;
import com.mig35.homeservice.data.repository.aircondition.IAirConditionSystemSettingsRepository;
import com.mig35.homeservice.data.repository.stereo.IStereoSystemSettingsRepository;
import com.mig35.homeservice.utils.rx.general.RxSchedulersAbs;

import dagger.Module;
import dagger.Provides;

@Module
@ActivityScope
public final class ControlElementModule {

    @Provides
    @ActivityScope
    public IAirConditionControlElementInteractor provideAirConditionControlElementInteractor(@NonNull final IAirConditionSystemElement airConditionSystemElement, @NonNull final IAirConditionSystemSettingsRepository airConditionSystemSettingsRepository, @NonNull final RxSchedulersAbs rxSchedulersAbs) {
        return new AirConditionControlElementInteractor(airConditionSystemElement, airConditionSystemSettingsRepository, rxSchedulersAbs);
    }

    @Provides
    @ActivityScope
    public ILightControlElementInteractor provideLightControlElementInteractor(@NonNull final ILightSystemElement lightSystemElement, @NonNull final RxSchedulersAbs rxSchedulersAbs) {
        return new LightControlElementInteractor(lightSystemElement, rxSchedulersAbs);
    }

    @Provides
    @ActivityScope
    public IStereoControlElementInteractor provideStereoControlElementInteractor(@NonNull final IStereoSystemElement stereoSystemElement, @NonNull final IStereoSystemSettingsRepository stereoSystemRepository, @NonNull final RxSchedulersAbs rxSchedulersAbs) {
        return new StereoControlElementInteractor(stereoSystemElement, stereoSystemRepository, rxSchedulersAbs);
    }
}
