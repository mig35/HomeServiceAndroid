package com.mig35.homeservice.utils.dagger;

import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public final class ComponentStore {

    private static final Map<String, Set<Object>> STORE = new HashMap<>(10);

    private ComponentStore() {
    }

    @NonNull
    @MainThread
    public static String nextKey() {
        String key;
        do {
            key = UUID.randomUUID().toString();
        } while (STORE.containsKey(key));
        return key;
    }

    @Nullable
    @MainThread
    public static Set<Object> getInjects(@NonNull final String key) {
        return STORE.get(key);
    }

    @MainThread
    public static void save(@NonNull final String key, @NonNull final Set<Object> injects) {
        STORE.put(key, injects);
    }

    @MainThread
    public static void remove(@NonNull final String key) {
        STORE.remove(key);
    }
}