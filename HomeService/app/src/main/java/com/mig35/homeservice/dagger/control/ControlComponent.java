package com.mig35.homeservice.dagger.control;

import android.support.annotation.NonNull;

import com.mig35.homeservice.dagger.ActivityScope;
import com.mig35.homeservice.ui.main.view.ControlActivity;

import dagger.Subcomponent;

@Subcomponent(modules = ControlModule.class)
@ActivityScope
public interface ControlComponent {

    void inject(@NonNull final ControlActivity controlActivity);
}