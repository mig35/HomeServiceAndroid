package com.mig35.homeservice.dagger.app;

import com.mig35.homeservice.business.system.aircondition.AirConditionSystemElement;
import com.mig35.homeservice.business.system.aircondition.IAirConditionSystemElement;
import com.mig35.homeservice.business.system.light.ILightSystemElement;
import com.mig35.homeservice.business.system.light.LightSystemElement;
import com.mig35.homeservice.business.system.stereo.IStereoSystemElement;
import com.mig35.homeservice.business.system.stereo.StereoSystemElement;
import com.mig35.homeservice.data.ControlElement;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public final class ControlElementsModule {

    @Singleton
    @Provides
    public IAirConditionSystemElement provideAirConditionSystemElement() {
        return new AirConditionSystemElement();
    }

    @Singleton
    @Provides
    public ILightSystemElement provideLightSystemElement() {
        return new LightSystemElement();
    }

    @Singleton
    @Provides
    public IStereoSystemElement provideStereoSystemElement() {
        return new StereoSystemElement();
    }

    @Singleton
    @Provides
    public ControlElement[] provideAllAvailableControlElements() {
        return ControlElement.values();
    }
}