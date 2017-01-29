package com.mig35.homeservice.business.system.stereo;

import android.support.annotation.IntRange;
import android.support.annotation.NonNull;

import com.mig35.homeservice.business.system.ISystemElement;

import java.util.Collection;

public interface IStereoSystemElement extends ISystemElement {

    int VOLUME_MIN = 0;
    int VOLUME_MAX = 100;

    void setPlaylist(@NonNull final Collection<String> songs);

    void setVolume(@IntRange(from = VOLUME_MIN, to = VOLUME_MAX) final int volume);
}