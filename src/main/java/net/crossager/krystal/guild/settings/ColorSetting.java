package net.crossager.krystal.guild.settings;

import net.crossager.krystal.utils.DefaultKrystalColor;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.awt.*;

public class ColorSetting implements GuildSetting<Color> {
    private Color color = new DefaultKrystalColor();
    @Override
    public String name() {
        return "color";
    }

    @Override
    public String description() {
        return "The color of embeds";
    }

    @Override
    public OptionData input() {
        return new OptionData(OptionType.STRING, "color", "New color, provide as hex", true).setRequiredLength(6, 6);
    }

    @Override
    public void parse(OptionMapping mapping) {
        try {
            color = Color.decode('#' + mapping.getAsString());
        } catch (NumberFormatException e) {
            throw new ParseException();
        }
    }

    @Override
    public Color get() {
        return color;
    }

    @Override
    public void set(Color value) {
        this.color = value;
    }

    @Override
    public String toStringRepresentation() {
        return Integer.toHexString(color.getRGB());
    }
}
