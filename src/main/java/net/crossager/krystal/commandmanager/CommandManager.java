package net.crossager.krystal.commandmanager;

import net.crossager.krystal.KrystalContext;
import net.crossager.krystal.guild.GuildProfile;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.Command;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;
import net.dv8tion.jda.api.interactions.commands.build.SubcommandData;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class CommandManager {
    private final KrystalContext context;
    private final List<KrystalCommand> commands = new LinkedList<>();
    private final JDA jda;
    private final List<Guild> guilds;

    public CommandManager(KrystalContext context) {
        this.guilds = new LinkedList<>();
        this.context = context;
        this.jda = context.jda();
        this.jda.addEventListener(new Listener());
    }

    public void registerGuild(Guild guild) {
        guilds.add(guild);
        putCommands(guild, commands);
    }

    public void registerCommands(List<KrystalCommand> commands) {
        this.commands.addAll(commands);
        guilds.forEach(guild -> putCommands(guild, commands));
    }

    private void putCommands(Guild guild, List<KrystalCommand> commands) {
        commands.stream().map(this::toJDACommand).forEach(command -> guild.upsertCommand(command).queue());
    }

    private CommandData toJDACommand(KrystalCommand command) {
        SlashCommandData data = Commands.slash(command.name(), command.description());
        if (command instanceof SubCommandedKrystalCommand subCommandedCommand)
            data.addSubcommands(subCommandedCommand.subCommands().stream().map(this::toJDASubcommand).collect(Collectors.toList()));
        else
            data.addOptions(command.options());
        return data;
    }

    private SubcommandData toJDASubcommand(KrystalCommand command) {
        return new SubcommandData(command.name(), command.description()).addOptions(command.options());
    }

    public List<KrystalCommand> getCommands() {
        return commands;
    }

    // this doesn't look right, does it?
    public void deleteCommands(List<KrystalCommand> commands) {
        this.commands.removeAll(commands);
        guilds.forEach(g -> g.retrieveCommands().queue(existingCommands -> {
            for (KrystalCommand command : commands) {
                for (Command existingCommand : existingCommands) {
                    if (command.name().equals(existingCommand.getName())) existingCommand.delete().queue();
                }
            }
        }));
    }

    private class Listener extends ListenerAdapter {
        @Override
        public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
            if (!event.isFromGuild()) return;
            if (!guilds.contains(event.getGuild())) return;
            Optional<GuildProfile> profile = context.guilds().get(event.getGuild());
            if (profile.isEmpty()) return;
            for (KrystalCommand command : commands) {
                if (command.name().equals(event.getName())) {
                    context.scheduler().runTaskAsynchronously(() -> command.onCommand(event, context, profile.get()));
                    break;
                }
            }
        }
    }
}
