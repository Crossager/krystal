package net.crossager.krystal.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public final class KrystalGson {
    private final Gson gson;

    public KrystalGson() {
        gson = new GsonBuilder().create();
    }

    public Gson gson() {
        return gson;
    }
}
