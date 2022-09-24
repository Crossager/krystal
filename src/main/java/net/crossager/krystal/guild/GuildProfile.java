package net.crossager.krystal.guild;

import net.crossager.krystal.KrystalContext;
import net.crossager.krystal.user.GuildUserProfile;
import net.dv8tion.jda.api.entities.Guild;

import java.awt.*;
import java.util.List;

public interface GuildProfile {
    List<Guild> getGuilds();
    boolean isShared();
    void addGuild(Guild guild);
    GuildUserProfileCache profiles();
    Color color();
    KrystalContext context();
}
