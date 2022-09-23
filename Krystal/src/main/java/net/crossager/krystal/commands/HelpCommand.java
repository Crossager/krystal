package net.crossager.krystal.commands;

import net.crossager.krystal.KrystalContext;
import net.crossager.krystal.commandmanager.KrystalCommand;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.Collection;
import java.util.List;

public class HelpCommand implements KrystalCommand {
    @Override
    public void onCommand(SlashCommandInteraction command, KrystalContext context) {
        command.reply("help").queue();
    }

    @Override
    public String name() {
        return "help";
    }

    @Override
    public String description() {
        return "Display all commands for this server";
    }

    @Override
    public Collection<OptionData> options() {
        return List.of();
    }
}
