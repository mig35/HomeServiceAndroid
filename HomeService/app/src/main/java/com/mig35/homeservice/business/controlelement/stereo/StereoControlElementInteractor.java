package com.mig35.homeservice.business.controlelement.stereo;

import android.support.annotation.NonNull;

import com.mig35.homeservice.business.controlelement.BaseControlElementInteractor;
import com.mig35.homeservice.business.system.stereo.IStereoSystemElement;
import com.mig35.homeservice.data.repository.stereo.IStereoSystemSettingsRepository;
import com.mig35.homeservice.utils.rx.general.RxSchedulersAbs;

import java.util.Collection;
import java.util.Collections;

public final class StereoControlElementInteractor extends BaseControlElementInteractor implements IStereoControlElementInteractor {

    @NonNull
    private final IStereoSystemElement mStereoSystemElement;
    @NonNull
    private final IStereoSystemSettingsRepository mSystemStereoRepository;

    public StereoControlElementInteractor(@NonNull final IStereoSystemElement stereoSystemElement, @NonNull final IStereoSystemSettingsRepository stereoSystemRepository, @NonNull final RxSchedulersAbs rxSchedulersAbs) {
        super(rxSchedulersAbs);

        mStereoSystemElement = stereoSystemElement;
        mSystemStereoRepository = stereoSystemRepository;
    }

    @Override
    protected void activateInner() {
        mStereoSystemElement.on();

        final Collection<String> playlist = mSystemStereoRepository.getPlaylist();
        mStereoSystemElement.setPlaylist(null == playlist ? Collections.emptyList() : playlist);

        final int volume = mSystemStereoRepository.getVolume();
        mStereoSystemElement.setVolume(
                Math.max(
                        IStereoSystemElement.VOLUME_MIN,
                        Math.min(IStereoSystemElement.VOLUME_MAX, volume)
                )
        );
    }

    @Override
    protected void deactivateInner() {
        mStereoSystemElement.off();
    }
}