package com.mig35.homeservice.business.system.aircondition;

import android.support.annotation.IntRange;

import com.mig35.homeservice.business.system.ISystemElement;

public interface IAirConditionSystemElement extends ISystemElement {

    int TEMPERATURE_MIN = 10;
    int TEMPERATURE_MAX = 34;

    void setTemperature(@IntRange(from = TEMPERATURE_MIN, to = TEMPERATURE_MAX) final int temperature);

    void high();

    void medium();

    void low();

    AirConditionSpeed getSpeed();
}