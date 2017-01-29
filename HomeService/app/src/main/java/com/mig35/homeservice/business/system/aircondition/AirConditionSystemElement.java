package com.mig35.homeservice.business.system.aircondition;

import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.Locale;

public final class AirConditionSystemElement implements IAirConditionSystemElement {

    private boolean mActive;
    @Nullable
    private Integer mTemperature;
    @Nullable
    private AirConditionSpeed mAirConditionSpeed;

    @Override
    public void setTemperature(@IntRange(from = TEMPERATURE_MIN, to = TEMPERATURE_MAX) final int temperature) {
        checkActiveState();

        if (TEMPERATURE_MIN > temperature || TEMPERATURE_MAX < temperature) {
            throw new IllegalArgumentException(String
                    .format(Locale.ENGLISH, "temperature should in the range: [%1$d, %2$d]", TEMPERATURE_MIN, TEMPERATURE_MAX));
        }
        mTemperature = temperature;
    }

    @Override
    public void high() {
        setSpeed(AirConditionSpeed.HIGH);
    }

    @Override
    public void medium() {
        setSpeed(AirConditionSpeed.MEDIUM);
    }

    @Override
    public void low() {
        setSpeed(AirConditionSpeed.LOW);
    }

    private void setSpeed(@NonNull final AirConditionSpeed airConditionSpeed) {
        checkActiveState();
        checkTemperature();

        mAirConditionSpeed = airConditionSpeed;
    }

    @Override
    public AirConditionSpeed getSpeed() {
        return mAirConditionSpeed;
    }

    @Override
    public void on() {
        mActive = true;
    }

    @Override
    public void off() {
        mActive = false;
        mTemperature = null;
        mAirConditionSpeed = null;
    }

    private void checkActiveState() {
        if (!mActive) {
            throw new RuntimeException("AirCondition is off!");
        }
    }

    private void checkTemperature() {
        if (null == mTemperature) {
            throw new RuntimeException("Temperature of AirCondition is not set!");
        }
    }
}