package net.crossager.krystal.guild;

import net.crossager.krystal.KrystalContext;
import net.crossager.krystal.commandmanager.CommandManager;
import net.crossager.krystal.user.GuildUserProfile;
import net.dv8tion.jda.api.entities.Guild;

import java.util.List;

public class CustomGuildProfile implements GuildProfile {
    private final long guildID;
    private final CommandManager commandManager;
    private final Guild guild;

    public CustomGuildProfile(Guild guild, KrystalContext context) {
        this.guild = guild;
        this.guildID = guild.getIdLong();
        this.commandManager = new CommandManager(context);
        commandManager.registerGuild(this.guild);
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
    public List<GuildUserProfile> profiles() {
        return null;
    }
}
