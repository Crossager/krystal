package net.crossager.krystal.guild;

import net.crossager.krystal.KrystalContext;
import net.crossager.krystal.economy.WorkStation;
import net.crossager.krystal.guild.settings.GuildSetting;
import net.dv8tion.jda.api.entities.Guild;

import java.awt.*;
import java.util.List;

public interface GuildProfile {
    List<Guild> getGuilds();
    boolean isShared();
    void addGuild(Guild guild);
    GuildUserProfileCache profiles();
    KrystalContext context();
    List<GuildSetting<?>> settings();
}
