package com.mig35.homeservice.data;

import android.support.annotation.StringRes;

import com.mig35.homeservice.R;

public enum ControlElement {

    STEREO(R.string.text_control_stereo),
    LIGHT(R.string.text_control_light),
    AIR_CONDITION(R.string.text_control_air_condition);

    public final int mNameId;

    ControlElement(@StringRes final int nameId) {
        mNameId = nameId;
    }
}