package net.crossager.krystal.utils;

import net.crossager.krystal.KrystalContext;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.requests.RestAction;

import java.awt.*;
import java.util.function.Function;
import java.util.function.Supplier;

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
}
