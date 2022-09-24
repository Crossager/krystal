package net.crossager.krystal.commands;

import net.crossager.krystal.KrystalContext;
import net.crossager.krystal.commandmanager.KrystalCommand;
import net.crossager.krystal.economy.WorkStation;
import net.crossager.krystal.guild.GuildProfile;
import net.crossager.krystal.user.GuildUserProfile;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.interactions.commands.Command;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.Collection;
import java.util.List;

public class WorkCommand implements KrystalCommand {
    private final List<WorkStation> workStations;

    public WorkCommand(List<WorkStation> workStations) {
        this.workStations = workStations;
    }

    @Override
    public void onCommand(SlashCommandInteraction command, KrystalContext context, GuildProfile guildProfile) {
        WorkStation workStation = workStations.get(command.getOption("work").getAsInt());
        GuildUserProfile userProfile = guildProfile.profiles().get(command.getUser());
        if (context.utilities().sendAndReturn(!userProfile.getCoolDowns().isTime(workStation), () ->
                command.replyEmbeds(context.utilities().error(command.getUser(), "Slow down", "`%s` will be ready %s".formatted(workStation.name(), userProfile.getCoolDowns().timeUntilFormatted(workStation)))).queue())) return;
        int money = context.random().range(workStation.min(), workStation.max());
        userProfile.addMoney(money);
        userProfile.getCoolDowns().set(workStation, workStation.coolDown());

        EmbedBuilder builder = context.utilities().newCommandEmbed(command.getUser());
        builder.addField(workStation.name(), "You earned " + money, false);
        command.replyEmbeds(builder.build()).queue();
    }

    @Override
    public String name() {
        return "work";
    }

    @Override
    public String description() {
        return "Get a job";
    }

    @Override
    public Collection<OptionData> options() {
        return List.of(new OptionData(OptionType.INTEGER, "work", "Where to work", true).addChoices(workStations.stream().map(w -> new Command.Choice(w.name(), workStations.indexOf(w))).toList()));
    }
}
