package net.crossager.krystal.guild;

import net.crossager.krystal.KrystalContext;
import net.crossager.krystal.economy.WorkStation;
import net.crossager.krystal.guild.settings.GuildSetting;
import net.crossager.krystal.guild.settings.GuildSettings;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.User;

import java.awt.*;
import java.util.List;

public interface GuildProfile {
    List<Guild> getGuilds();
    boolean isShared();
    void addGuild(Guild guild);
    GuildUserProfileCache profiles();
    KrystalContext context();
    GuildSettings settings();
    EmbedBuilder newCommandEmbed(User user);
}
