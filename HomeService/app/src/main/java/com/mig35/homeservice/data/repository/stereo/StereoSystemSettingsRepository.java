package com.mig35.homeservice.data.repository.stereo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public final class StereoSystemSettingsRepository implements IStereoSystemSettingsRepository {

    private final List<String> mPlaylist;
    private final AtomicInteger mVolume;

    public StereoSystemSettingsRepository() {
        mPlaylist = new ArrayList<>();
        mPlaylist.add("song 0");
        mPlaylist.add("song 1");
        mPlaylist.add("song 2");

        mVolume = new AtomicInteger(0);
    }

    @Override
    public Collection<String> getPlaylist() {
        return Collections.unmodifiableList(mPlaylist);
    }

    @Override
    public int getVolume() {
        return mVolume.get();
    }
}