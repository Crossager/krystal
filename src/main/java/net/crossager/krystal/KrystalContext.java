package net.crossager.krystal;

import com.google.gson.Gson;
import net.crossager.krystal.gson.KrystalGson;
import net.crossager.krystal.guild.GuildProfileCache;
import net.crossager.krystal.schedule.Scheduler;
import net.crossager.krystal.sharedimpl.SharedGuildProfile;
import net.crossager.krystal.utils.CustomKrystalLogger;
import net.crossager.krystal.utils.KrystalUtilities;
import net.dv8tion.jda.api.JDA;

import java.awt.*;
import java.util.Random;
import java.util.logging.Logger;

public class KrystalContext {
    private final Gson gson = new KrystalGson().gson();
    private final GuildProfileCache guildProfiles;
    private final JDA jda;
    private final Logger logger = new CustomKrystalLogger();
    private final Random random = new Random();
    private final Scheduler scheduler = new Scheduler();

    public KrystalContext(JDA jda) {
        this.jda = jda;
        guildProfiles = new GuildProfileCache(new SharedGuildProfile(this), this);
    }


    public Scheduler scheduler() {
        return scheduler;
    }

    public Gson gson() {
        return gson;
    }

    public JDA jda() {
        return jda;
    }

    public GuildProfileCache guilds() {
        return guildProfiles;
    }

    public Logger logger() {
        return logger;
    }

    public Random random() {
        return random;
    }

    public Color getColor() {
        return new Color(120, 0, 248);
    }

    public KrystalUtilities utilities() {
        return new KrystalUtilities(this);
    }
}
