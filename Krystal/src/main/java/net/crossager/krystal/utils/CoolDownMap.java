package net.crossager.krystal.utils;

import java.util.HashMap;

public class CoolDownMap extends HashMap<Enum<?>, Long> {
    @Override
    public Long get(Object key) {
        return super.getOrDefault(key, 0L);
    }

    public boolean isTime(Enum<?> o) {
        return get(o) < System.currentTimeMillis();
    }

    public String timeUntilFormatted(Enum<?> o) {
        int seconds = (int) (get(o) / 1000);
        int minutes = seconds / 60;
        int hours = minutes / 60;
        int days = hours / 24;
        seconds = seconds % 60;
        minutes = minutes % 60;
        hours = hours % 24;
        StringBuilder builder = new StringBuilder();
        if (days != 0) builder.append(days).append(" days, ");
        if (hours != 0) builder.append(hours).append(" hours, ");
        if (minutes != 0) builder.append(minutes).append(" minutes");
        builder.append(seconds).append(" seconds");
        return builder.toString();
    }

    public long timeUntil(Enum<?> o) {
        return System.currentTimeMillis() - get(o);
    }

    public void set(Enum<?> o, long time) {
        put(o, System.currentTimeMillis() + time);
    }

    public void add(Enum<?> o, long amount) {
        put(o, get(o) + amount);
    }
}
