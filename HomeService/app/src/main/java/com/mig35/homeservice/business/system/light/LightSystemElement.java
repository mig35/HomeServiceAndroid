package com.mig35.homeservice.business.system.light;

public final class LightSystemElement implements ILightSystemElement {

    private boolean mActive;

    @Override
    public void on() {
        mActive = true;
    }

    @Override
    public void off() {
        mActive = false;
    }
}