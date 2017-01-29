package com.mig35.homeservice.utils.rx.general;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

public final class RxSchedulersTest extends RxSchedulersAbs {

    @Override
    public Scheduler getMainThreadScheduler() {
        return Schedulers.trampoline();
    }

    @Override
    public Scheduler getIOScheduler() {
        return Schedulers.trampoline();
    }

    @Override
    public Scheduler getComputationScheduler() {
        return Schedulers.trampoline();
    }
}