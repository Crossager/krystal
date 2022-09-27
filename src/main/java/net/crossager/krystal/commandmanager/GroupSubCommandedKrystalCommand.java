package net.crossager.krystal.commandmanager;

import net.crossager.krystal.KrystalContext;
import net.crossager.krystal.guild.GuildProfile;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.Collection;
import java.util.List;

public abstract class GroupSubCommandedKrystalCommand extends SubCommandedKrystalCommand {
    @Override
    public void onCommand(SlashCommandInteraction command, KrystalContext context, GuildProfile guildProfile) {
        if (command.getSubcommandGroup() == null) throw new IllegalStateException("Command has groups but no group name is provided");

        for (SubCommandedKrystalCommand subCommand : subCommandGroups()) {
            if (subCommand.name().equals(command.getSubcommandGroup())) subCommand.onCommand(command, context, guildProfile);
        }
        if (!command.isAcknowledged()) command.replyEmbeds(context.utilities().internalError()).setEphemeral(true).queue();
    }

    @Override
    public Collection<KrystalCommand> subCommands() {
        return List.of();
    }

    public abstract Collection<SubCommandedKrystalCommand> subCommandGroups();
}
