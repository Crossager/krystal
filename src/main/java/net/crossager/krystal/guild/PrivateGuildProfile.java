package net.crossager.krystal.guild;

import net.crossager.krystal.KrystalContext;
import net.crossager.krystal.commandmanager.CommandManager;
import net.crossager.krystal.economy.DefaultWorkStations;
import net.crossager.krystal.economy.WorkStation;
import net.crossager.krystal.guild.settings.GuildSetting;
import net.dv8tion.jda.api.entities.Guild;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class PrivateGuildProfile implements GuildProfile {
    private final long guildID;
    private final CommandManager commandManager;
    private final Guild guild;
    private final GuildUserProfileCache profiles;
    private final KrystalContext context;


    public PrivateGuildProfile(Guild guild, KrystalContext context) {
        this.guild = guild;
        this.guildID = guild.getIdLong();
        this.commandManager = new CommandManager(context);
        commandManager.registerGuild(this.guild);
        profiles = new GuildUserProfileCache(this);
        this.context = context;
    }

    public CommandManager commands() {
        return commandManager;
    }

    @Override
    public List<Guild> getGuilds() {
        return List.of(guild);
    }

    @Override
    public boolean isShared() {
        return false;
    }

    @Override
    public void addGuild(Guild guild) {

    }

    @Override
    public GuildUserProfileCache profiles() {
        return profiles;
    }

    @Override
    public KrystalContext context() {
        return context;
    }

    @Override
    public List<GuildSetting<?>> settings() {
        return null;
    }
}
