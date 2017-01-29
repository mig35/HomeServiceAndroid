package com.mig35.homeservice.business.controlelement.aircondition;

import android.support.annotation.NonNull;

import com.mig35.homeservice.business.controlelement.BaseControlElementInteractor;
import com.mig35.homeservice.business.system.aircondition.IAirConditionSystemElement;
import com.mig35.homeservice.data.repository.aircondition.IAirConditionSystemSettingsRepository;
import com.mig35.homeservice.utils.rx.general.RxSchedulersAbs;

public final class AirConditionControlElementInteractor extends BaseControlElementInteractor implements IAirConditionControlElementInteractor {

    @NonNull
    private final IAirConditionSystemElement mAirConditionSystemElement;
    @NonNull
    private final IAirConditionSystemSettingsRepository mAirConditionSystemSettingsRepository;

    public AirConditionControlElementInteractor(@NonNull final IAirConditionSystemElement airConditionSystemElement, @NonNull final IAirConditionSystemSettingsRepository airConditionSystemSettingsRepository, @NonNull final RxSchedulersAbs rxSchedulersAbs) {
        super(rxSchedulersAbs);

        mAirConditionSystemElement = airConditionSystemElement;
        mAirConditionSystemSettingsRepository = airConditionSystemSettingsRepository;
    }

    @Override
    protected void activateInner() {
        mAirConditionSystemElement.on();

        final int temperature = mAirConditionSystemSettingsRepository.getTemperature();
        mAirConditionSystemElement.setTemperature(
                Math.max(
                        IAirConditionSystemElement.TEMPERATURE_MIN,
                        Math.min(IAirConditionSystemElement.TEMPERATURE_MAX, temperature)
                )
        );

        mAirConditionSystemElement.medium();
    }

    @Override
    protected void deactivateInner() {
        mAirConditionSystemElement.off();
    }
}