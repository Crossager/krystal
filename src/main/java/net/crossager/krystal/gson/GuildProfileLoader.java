package net.crossager.krystal.gson;

import net.crossager.krystal.KrystalContext;
import net.crossager.krystal.guild.PrivateGuildProfile;
import net.crossager.krystal.guild.GuildProfile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class GuildProfileLoader {
    private final PrivateGuildProfile profile;

    public GuildProfileLoader(Path path, KrystalContext context) {
        try {
            profile = context.gson().fromJson(Files.readString(path), PrivateGuildProfile.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public GuildProfile profile() {
        return profile;
    }
}
