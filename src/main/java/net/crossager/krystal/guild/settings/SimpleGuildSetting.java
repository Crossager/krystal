package net.crossager.krystal.guild.settings;

import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

public class SimpleGuildSetting<T> implements GuildSetting<T> {
    private final String name;
    private final String description;
    private final OptionType inputType;
    private T value;

    public SimpleGuildSetting(String name, String description, OptionType inputType, T defaultValue) {
        this.name = name;
        this.description = description;
        this.inputType = inputType;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public String description() {
        return description;
    }

    @Override
    public OptionData input() {
        return new OptionData(inputType, "value", "Setting value", true);
    }

    @Override
    public void parse(OptionMapping mapping) {
        value = new OptionMappingParser<T>(inputType, mapping).get();
    }

    @Override
    public T get() {
        return value;
    }

    @Override
    public void set(T value) {
        this.value = value;
    }

    @Override
    public String toStringRepresentation() {
        return null;
    }
}
