package com.mig35.homeservice.business.controlelement.light;

import android.support.annotation.NonNull;

import com.mig35.homeservice.business.controlelement.BaseControlElementInteractor;
import com.mig35.homeservice.business.system.light.ILightSystemElement;
import com.mig35.homeservice.utils.rx.general.RxSchedulersAbs;

public final class LightControlElementInteractor extends BaseControlElementInteractor implements ILightControlElementInteractor {

    @NonNull
    private final ILightSystemElement mLightSystemElement;

    public LightControlElementInteractor(@NonNull final ILightSystemElement lightSystemElement, @NonNull final RxSchedulersAbs rxSchedulersAbs) {
        super(rxSchedulersAbs);

        mLightSystemElement = lightSystemElement;
    }

    @Override
    protected void activateInner() {
        mLightSystemElement.on();
    }

    @Override
    protected void deactivateInner() {
        mLightSystemElement.off();
    }
}