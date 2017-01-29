package com.mig35.homeservice.dagger.app;

import com.mig35.homeservice.data.repository.aircondition.AirConditionSystemSettingsRepository;
import com.mig35.homeservice.data.repository.aircondition.IAirConditionSystemSettingsRepository;
import com.mig35.homeservice.data.repository.stereo.IStereoSystemSettingsRepository;
import com.mig35.homeservice.data.repository.stereo.StereoSystemSettingsRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
@Singleton
public final class RepositoryModule {

    @Provides
    @Singleton
    public IAirConditionSystemSettingsRepository provideAirConditionSystemRepository() {
        return new AirConditionSystemSettingsRepository();
    }

    @Provides
    @Singleton
    public IStereoSystemSettingsRepository provideStereoSystemRepository() {
        return new StereoSystemSettingsRepository();
    }
}