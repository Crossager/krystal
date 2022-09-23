package net.crossager.krystal.sharedimpl;

import net.crossager.krystal.KrystalContext;
import net.crossager.krystal.commandmanager.CommandManager;
import net.crossager.krystal.commandmanager.DefaultKrystalCommands;
import net.crossager.krystal.guild.GuildProfile;
import net.crossager.krystal.user.GuildUserProfile;
import net.dv8tion.jda.api.entities.Guild;

import java.util.LinkedList;
import java.util.List;

public class SharedGuildProfile implements GuildProfile {
    private final CommandManager commandManager;
    private final List<Guild> guilds = new LinkedList<>();

    public SharedGuildProfile(KrystalContext context) {
        commandManager = new CommandManager(context);
        commandManager.registerCommands(new DefaultKrystalCommands());
    }

    @Override
    public void addGuild(Guild guild) {
        commandManager.registerGuild(guild);
    }

    @Override
    public List<GuildUserProfile> profiles() {
        return null;
    }

    @Override
    public List<Guild> getGuilds() {
        return guilds;
    }

    @Override
    public boolean isShared() {
        return true;
    }
}
