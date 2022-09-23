package net.crossager.krystal.commandmanager;

import net.crossager.krystal.KrystalContext;
import net.crossager.krystal.guild.GuildProfile;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.Collection;

public interface KrystalCommand {
    void onCommand(SlashCommandInteraction command, KrystalContext context, GuildProfile guildProfile);
    String name();
    String description();
    Collection<OptionData> options();
}
