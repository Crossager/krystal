package net.crossager.krystal.gson;

import com.google.gson.internal.bind.ReflectiveTypeAdapterFactory;
import net.crossager.krystal.KrystalContext;
import net.crossager.krystal.guild.CustomGuildProfile;
import net.crossager.krystal.guild.GuildProfile;

import java.io.*;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;

public class GuildProfileLoader {
    private final CustomGuildProfile profile;

    public GuildProfileLoader(Path path, KrystalContext context) {
        try {
            profile = context.gson().fromJson(Files.readString(path), CustomGuildProfile.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public GuildProfile profile() {
        return profile;
    }
}
