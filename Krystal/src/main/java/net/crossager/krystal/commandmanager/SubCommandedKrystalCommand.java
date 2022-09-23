package net.crossager.krystal.commandmanager;

import net.crossager.krystal.KrystalContext;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.Collection;
import java.util.List;

public abstract class SubCommandedKrystalCommand implements KrystalCommand {
    @Override
    public void onCommand(SlashCommandInteraction command, KrystalContext context) {
        if (command.getSubcommandName() == null) throw new IllegalStateException("Command has subcommands but no subcommand name is provided");
        for (KrystalCommand subCommand : subCommands()) {
            if (subCommand.name().equals(command.getSubcommandName())) subCommand.onCommand(command, context);
        }
        if (!command.isAcknowledged()) command.replyEmbeds(context.utilities().internalError()).setEphemeral(true).queue();
    }

    @Override
    public final Collection<OptionData> options() {
        return List.of();
    }

    public abstract Collection<KrystalCommand> subCommands();
}
