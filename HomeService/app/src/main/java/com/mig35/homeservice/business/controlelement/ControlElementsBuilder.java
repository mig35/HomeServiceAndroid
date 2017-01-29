package com.mig35.homeservice.business.controlelement;

import android.support.annotation.NonNull;

import com.mig35.homeservice.business.controlelement.aircondition.IAirConditionControlElementInteractor;
import com.mig35.homeservice.business.controlelement.light.ILightControlElementInteractor;
import com.mig35.homeservice.business.controlelement.stereo.IStereoControlElementInteractor;
import com.mig35.homeservice.data.ControlElement;

public final class ControlElementsBuilder implements IControlElementsBuilder {

    @NonNull
    private final IStereoControlElementInteractor mStereoControlElementInteractor;
    @NonNull
    private final ILightControlElementInteractor mLightElementInteractor;
    @NonNull
    private final IAirConditionControlElementInteractor mAirConditionElementInteractor;

    public ControlElementsBuilder(@NonNull final IStereoControlElementInteractor stereoControlElementInteractor, @NonNull final ILightControlElementInteractor lightElementInteractor, @NonNull final IAirConditionControlElementInteractor airConditionElementInteractor) {
        mStereoControlElementInteractor = stereoControlElementInteractor;
        mLightElementInteractor = lightElementInteractor;
        mAirConditionElementInteractor = airConditionElementInteractor;
    }

    @Override
    @NonNull
    public IControlElementInteractor createInteractor(@NonNull final ControlElement controlElement) {
        switch (controlElement) {
            case STEREO:
                return mStereoControlElementInteractor;
            case LIGHT:
                return mLightElementInteractor;
            case AIR_CONDITION:
                return mAirConditionElementInteractor;
            default:
                throw new AssertionError("Can not create IControlElementInteractor for: " + controlElement);
        }
    }
}