package net.crossager.krystal.guild.settings;

import net.crossager.krystal.economy.DefaultWorkStations;
import net.crossager.krystal.economy.WorkStation;
import net.crossager.krystal.utils.DefaultKrystalColor;
import net.dv8tion.jda.api.interactions.commands.OptionType;

import java.awt.*;
import java.util.List;


public class GuildSettings {
//    private final GuildSetting<List<WorkStation>> workStations = new SimpleGuildSetting<>("work-stations", "The places where you can work", OptionType.)
    private final GuildSetting<Color> color = new SimpleGuildSetting<>("color", "The color of embeds", OptionType.STRING, new DefaultKrystalColor());


    public GuildSetting<Color> color() {
        return color;
    }
}
