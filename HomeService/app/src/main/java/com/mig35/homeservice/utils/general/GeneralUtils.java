package com.mig35.homeservice.utils.general;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.Arrays;

public final class GeneralUtils {

    private GeneralUtils() {
    }

    public static boolean equals(@Nullable final Object a, @Nullable @SuppressWarnings("StandardVariableNames") final Object b) {
        return null == a ? null == b : a.equals(b);
    }

    public static int hash(final Object... values) {
        return Arrays.hashCode(values);
    }

    public static int compare(final long lhs, final long rhs) {
        //noinspection UnnecessaryParentheses,NestedConditionalExpression
        return lhs < rhs ? -1 : (lhs == rhs ? 0 : 1);
    }

    public static void commitNow(@NonNull final FragmentTransaction transaction, @NonNull final FragmentManager fm) {
        //noinspection ConstantOnRightSideOfComparison
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            transaction.commitNow();
        } else {
            transaction.commit();
            fm.executePendingTransactions();
        }
    }
}