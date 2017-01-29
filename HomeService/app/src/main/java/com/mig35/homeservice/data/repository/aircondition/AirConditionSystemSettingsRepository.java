package com.mig35.homeservice.data.repository.aircondition;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public final class AirConditionSystemSettingsRepository implements IAirConditionSystemSettingsRepository {

    private static final int TEMPERATURE_WINTER = 30;
    private static final int TEMPERATURE_OTHER = 22;

    private static final List<Integer> WINTER_MONTHS = new ArrayList<>();

    static {
        WINTER_MONTHS.add(Calendar.OCTOBER);
        WINTER_MONTHS.add(Calendar.NOVEMBER);
        WINTER_MONTHS.add(Calendar.DECEMBER);
        WINTER_MONTHS.add(Calendar.JANUARY);
        WINTER_MONTHS.add(Calendar.FEBRUARY);
        WINTER_MONTHS.add(Calendar.MARCH);
    }

    @Override
    public int getTemperature() {
        final Calendar calendar = Calendar.getInstance();
        final int month = calendar.get(Calendar.MONTH);
        if (isWinter(month)) {
            return TEMPERATURE_WINTER;
        }
        return TEMPERATURE_OTHER;
    }

    private static boolean isWinter(final int month) {
        return WINTER_MONTHS.contains(month);
    }
}