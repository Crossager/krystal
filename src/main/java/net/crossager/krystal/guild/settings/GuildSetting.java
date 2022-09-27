package net.crossager.krystal.guild.settings;

import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

public interface GuildSetting<T> {
    String name();
    String description();
    OptionData input();
    void parse(OptionMapping mapping);
    T get();
    void set(T value);
    String toStringRepresentation();
}
