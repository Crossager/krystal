package net.crossager.krystal.guild.settings;

import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;

public class OptionMappingParser<T> {
    private final Object value;

    public OptionMappingParser(OptionType type, OptionMapping mapping) {
        switch (type) {
            case BOOLEAN -> value = mapping.getAsBoolean();
            case INTEGER -> value = mapping.getAsInt();
            case NUMBER -> value = mapping.getAsDouble();
            case ATTACHMENT -> value = mapping.getAsAttachment();
            case USER -> value = mapping.getAsUser();
            case STRING -> value = mapping.getAsString();
            default -> throw new IllegalStateException("Invalid type provided");
        }
    }

    @SuppressWarnings("unchecked")
    public T get() {
        return (T) value;
    }
}
