package com.mig35.homeservice.utils.general;

import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public final class Guard {

    private Guard() {
    }

    /**
     * @throws AssertionError if current thread is not MainLooper thread
     */
    public static void assertUiThread() {
        if (!GeneralUtils.equals(Looper.getMainLooper(), Looper.myLooper())) {
            throw new AssertionError("current thread is not main thread");
        }
    }

    /**
     * @throws AssertionError if o is null
     */
    public static void assertNotNull(@Nullable final Object o, @NonNull final String errorMessage) {
        if (null == o) {
            throw new AssertionError(errorMessage);
        }
    }

    /**
     * @throws AssertionError if o is null
     */
    public static void assertNull(@Nullable final Object o, @NonNull final String errorMessage) {
        if (null != o) {
            throw new AssertionError(errorMessage);
        }
    }

    /**
     * @throws AssertionError if o is null
     */
    public static void assertTrue(final boolean o, @NonNull final String errorMessage) {
        if (!o) {
            throw new AssertionError(errorMessage);
        }
    }
}