package com.mig35.homeservice.business.controlelement;

import android.support.annotation.NonNull;

import com.mig35.homeservice.data.ControlElement;

public interface IControlElementsBuilder {

    @NonNull
    IControlElementInteractor createInteractor(@NonNull final ControlElement controlElement);
}