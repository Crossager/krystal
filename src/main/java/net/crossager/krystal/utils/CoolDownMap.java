package net.crossager.krystal.utils;

import java.util.HashMap;

public class CoolDownMap extends HashMap<Identifiable, Long> {
    @Override
    public Long get(Object key) {
        return super.getOrDefault(key, 0L);
    }

    public boolean isTime(Identifiable id) {
        return get(id) < System.currentTimeMillis();
    }

    public String timeUntilFormatted(Identifiable o) {
        return "<t:" + (get(o) / 1000) + ":R>";
    }

    public long timeUntil(Identifiable id) {
        return System.currentTimeMillis() - get(id);
    }

    public void set(Identifiable id, long time) {
        put(id, System.currentTimeMillis() + time);
    }

    public void add(Identifiable id, long amount) {
        put(id, get(id) + amount);
    }
}
