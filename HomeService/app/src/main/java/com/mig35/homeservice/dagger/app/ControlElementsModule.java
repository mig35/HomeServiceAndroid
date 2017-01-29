package com.mig35.homeservice.dagger.app;

import com.mig35.homeservice.data.ControlElement;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public final class ControlElementsModule {

    @Singleton
    @Provides
    public ControlElement[] provideAllAvailableControlElements() {
        return ControlElement.values();
    }
}