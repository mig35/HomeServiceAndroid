package com.mig35.homeservice.ui.common.base;

import android.support.annotation.NonNull;

import com.mig35.homeservice.ui.common.view.base.IBaseView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

final class ViewCommandState<T extends IBaseView> {

    private final Collection<ViewCommand<T>> mViewCommands = new ArrayList<>(10);

    private boolean mTerminate;

    public void apply(@NonNull final T view) {
        apply(Collections.singletonList(view));
    }

    public void apply(@NonNull final Iterable<T> views) {
        for (final ViewCommand<T> viewCommand : mViewCommands) {
            for (final T view : views) {
                viewCommand.execute(view);
            }
        }
    }

    public void clear() {
        mViewCommands.clear();
    }

    public void addCommand(@NonNull final ViewCommand<T> viewCommand, @NonNull final Iterable<T> views) {
        if (mTerminate) {
            return;
        }
        mViewCommands.add(viewCommand);

        executeCommand(viewCommand, views);
    }

    public void addCommandNoHistory(@NonNull final ViewCommand<T> viewCommand, @NonNull final Iterable<T> views) {
        if (mTerminate) {
            return;
        }

        executeCommand(viewCommand, views);
    }

    private void executeCommand(@NonNull final ViewCommand<T> viewCommand, @NonNull final Iterable<T> views) {
        for (final T view : views) {
            viewCommand.execute(view);
        }
    }

    public void terminate() {
        mTerminate = true;
    }
}