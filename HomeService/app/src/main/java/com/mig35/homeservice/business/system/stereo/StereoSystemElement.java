package com.mig35.homeservice.business.system.stereo;

import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.mig35.homeservice.utils.general.Guard;

import java.util.Collection;
import java.util.Collections;
import java.util.Locale;

public final class StereoSystemElement implements IStereoSystemElement {

    private boolean mActive;
    @Nullable
    private Integer mVolume;
    @Nullable
    private Collection<String> mSongs;

    @Override
    public void setPlaylist(@NonNull final Collection<String> songs) {
        checkActiveState();
        Guard.assertNotNull(songs, "songs can not be null");

        mSongs = Collections.unmodifiableCollection(songs);
    }

    @Override
    public void setVolume(@IntRange(from = VOLUME_MIN, to = VOLUME_MAX) final int volume) {
        checkActiveState();
        Guard.assertNotNull(mSongs, "songs can not be null");

        if (VOLUME_MIN > volume || VOLUME_MAX < volume) {
            throw new IllegalArgumentException(String.format(Locale.ENGLISH, "volume should in the range: [%1$d, %2$d]", VOLUME_MIN, VOLUME_MAX));
        }
        mVolume = volume;
    }

    @Override
    public void on() {
        mActive = true;
    }

    @Override
    public void off() {
        mSongs = null;
        mVolume = null;
        mActive = false;
    }

    private void checkActiveState() {
        if (!mActive) {
            throw new RuntimeException("Stereo is off!");
        }
    }
}