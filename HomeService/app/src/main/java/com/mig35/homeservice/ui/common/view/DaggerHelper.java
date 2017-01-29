package com.mig35.homeservice.ui.common.view;

import android.support.annotation.NonNull;

import com.mig35.homeservice.ui.common.base.IBasePresenter;
import com.mig35.homeservice.ui.common.view.base.IBaseView;
import com.mig35.homeservice.utils.dagger.ComponentStore;
import com.mig35.homeservice.utils.general.GeneralUtils;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

public final class DaggerHelper implements Serializable {

    private static final long serialVersionUID = 5136274762479535980L;

    private final String mKey;
    private transient List<Field> mFields;

    public DaggerHelper() {
        mKey = ComponentStore.nextKey();
    }

    public void onCreate(@NonNull final Object injectObject, @NonNull final Class<?> finishClass, @NonNull final DaggerInjector daggerInjector) {
        mFields = new ArrayList<>(2);
        collectAllInjectFields(injectObject.getClass(), finishClass, mFields);

        final Set<Object> injects = ComponentStore.getInjects(mKey);
        if (null == injects) {
            doInjects(injectObject, daggerInjector, mFields);
        } else {
            restoreInjects(injectObject, injects, mFields);
        }
    }

    public void bindView(final IBaseView baseView) {
        for (final Field field : mFields) {
            try {
                final Object injectedField = field.get(baseView);
                if (injectedField instanceof IBasePresenter) {
                    //noinspection unchecked,rawtypes
                    ((IBasePresenter) injectedField).bindView(baseView);
                }
            } catch (final IllegalAccessException ignored) {
                // pass
            }
        }
    }

    public void unbindView(final IBaseView baseView) {
        for (final Field field : mFields) {
            try {
                final Object injectedField = field.get(baseView);
                if (injectedField instanceof IBasePresenter) {
                    //noinspection unchecked,rawtypes
                    ((IBasePresenter) injectedField).unbindView(baseView);
                }
            } catch (final IllegalAccessException ignored) {
                // pass
            }
        }
    }

    public void onDestroy(final boolean shouldDestroy) {
        if (shouldDestroy) {
            ComponentStore.remove(mKey);
        }
        //noinspection AssignmentToNull
        mFields = null;
    }

    private void doInjects(final Object injectObject, final DaggerInjector daggerInjector, final Collection<Field> fields) {
        daggerInjector.doInjects();
        saveInjects(injectObject, fields);
    }

    private void saveInjects(@NonNull final Object injectObject, @NonNull final Collection<Field> fields) {
        final Set<Object> injects = new HashSet<>(fields.size());
        for (final Field field : fields) {
            try {
                injects.add(field.get(injectObject));
            } catch (final IllegalAccessException e) {
                //noinspection ProhibitedExceptionThrown
                throw new RuntimeException(e);
            }
        }
        ComponentStore.save(mKey, injects);
    }

    private static void restoreInjects(@NonNull final Object injectObject, @NonNull final Iterable<Object> injects, @NonNull final Iterable<Field> fields) {
        for (final Field field : fields) {
            final Class<?> neededClass = field.getType();
            for (final Object inject : injects) {
                if (neededClass.isInstance(inject)) {
                    try {
                        field.set(injectObject, inject);
                        break;
                    } catch (final IllegalAccessException e) {
                        //noinspection ProhibitedExceptionThrown
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

    private static void collectAllInjectFields(@NonNull final Class<?> currentClass, @NonNull final Class<?> finishClass, @NonNull final Collection<Field> injectFields) {
        if (!currentClass.equals(finishClass)) {
            for (final Field field : currentClass.getDeclaredFields()) {
                for (final Annotation annotation : field.getDeclaredAnnotations()) {
                    if (GeneralUtils.equals(Inject.class, annotation.annotationType())) {
                        if (!field.isAccessible()) {
                            field.setAccessible(true);
                        }
                        injectFields.add(field);
                    }
                }
            }
        }
    }

    public interface DaggerInjector {

        void doInjects();
    }
}