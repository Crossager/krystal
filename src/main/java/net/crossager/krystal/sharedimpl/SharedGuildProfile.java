package net.crossager.krystal.sharedimpl;

import net.crossager.krystal.KrystalContext;
import net.crossager.krystal.commandmanager.CommandManager;
import net.crossager.krystal.commandmanager.DefaultKrystalCommands;
import net.crossager.krystal.guild.GuildProfile;
import net.crossager.krystal.guild.GuildUserProfileCache;
import net.crossager.krystal.user.GuildUserProfile;
import net.dv8tion.jda.api.entities.Guild;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class SharedGuildProfile implements GuildProfile {
    private final CommandManager commandManager;
    private final List<Guild> guilds = new LinkedList<>();
    private final GuildUserProfileCache profiles;
    private final KrystalContext context;

    public SharedGuildProfile(KrystalContext context) {
        commandManager = new CommandManager(context);
        commandManager.registerCommands(new DefaultKrystalCommands());
        profiles = new GuildUserProfileCache(this);
        this.context = context;
    }

    @Override
    public void addGuild(Guild guild) {
        commandManager.registerGuild(guild);
    }

    @Override
    public List<Guild> getGuilds() {
        return guilds;
    }

    @Override
    public boolean isShared() {
        return true;
    }

    @Override
    public GuildUserProfileCache profiles() {
        return profiles;
    }

    @Override
    public Color color() {
        return context.getColor();
    }

    @Override
    public KrystalContext context() {
        return context;
    }
}
