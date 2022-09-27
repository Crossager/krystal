package net.crossager.krystal.guild.settings;

import java.util.List;

public class GuildSettings {
//    private final GuildSetting<List<WorkStation>> workStations = new SimpleGuildSetting<>("work-stations", "The places where you can work", OptionType.)
    private final ColorSetting color = new ColorSetting();


    public ColorSetting color() {
        return color;
    }

    public List<GuildSetting<?>> allSettings() {
        return List.of(color);
    }
}
