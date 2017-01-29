package com.mig35.homeservice.data.repository.stereo;

import android.support.annotation.Nullable;

import java.util.Collection;

public interface IStereoSystemSettingsRepository {

    @Nullable
    Collection<String> getPlaylist();

    int getVolume();
}