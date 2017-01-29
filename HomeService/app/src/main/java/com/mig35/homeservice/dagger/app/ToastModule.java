package com.mig35.homeservice.dagger.app;

import android.content.Context;
import android.support.annotation.NonNull;

import com.mig35.homeservice.utils.android.ToastShower;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public final class ToastModule {

    @Singleton
    @Provides
    public ToastShower provideToastShower(@NonNull final Context context) {
        return new ToastShower(context);
    }
}