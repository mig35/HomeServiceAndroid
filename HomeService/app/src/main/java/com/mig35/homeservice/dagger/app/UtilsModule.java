package com.mig35.homeservice.dagger.app;


import com.mig35.homeservice.utils.rx.general.RxSchedulers;
import com.mig35.homeservice.utils.rx.general.RxSchedulersAbs;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public final class UtilsModule {

    @Singleton
    @Provides
    public RxSchedulersAbs provideRxSchedulers() {
        return new RxSchedulers();
    }
}