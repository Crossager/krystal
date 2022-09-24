package net.crossager.krystal.utils;

import net.crossager.krystal.KrystalContext;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

import java.awt.*;

public class KrystalUtilities {
    private final KrystalContext context;

    public KrystalUtilities(KrystalContext context) {
        this.context = context;
    }

    public MessageEmbed internalError() {
        return new EmbedBuilder().setColor(Color.RED).addField("Internal error", "Please report this to the dev team", false).build();
    }

    public MessageEmbed invalidPermission(Permission permission) {
        return new EmbedBuilder().setColor(Color.RED).addField("Invalid permissions", "You need the `" + permission.getName() + "` to do this", false).build();
    }

    public boolean sendAndReturn(boolean condition, Runnable runnable) {
        if (condition) runnable.run();
        return condition;
    }

    public EmbedBuilder newCommandEmbed(User user) {
        return new EmbedBuilder().setColor(context.getColor()).setFooter(user.getAsTag(), user.getAvatarUrl());
    }

    public MessageEmbed error(User user, String message) {
        return newCommandEmbed(user).setColor(Color.RED).addField("Error", message, false).build();
    }

    public MessageEmbed error(User user, String title,  String message) {
        return newCommandEmbed(user).setColor(Color.RED).addField(title, message, false).build();
    }

    public Button inactivity() {
        return Button.secondary("null", "Closed due to inactivity").withDisabled(true);
    }
}

