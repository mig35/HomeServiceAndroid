package com.mig35.homeservice.ui.common.model;

import android.support.annotation.Nullable;

@SuppressWarnings("EnumeratedConstantNamingConvention")
public enum UserCommand {

    ON,
    OFF;

    @Nullable
    public UserCommand undoCommand() {
        switch (this) {
            case ON:
                return OFF;
            case OFF:
                return ON;
        }
        return null;
    }
}