package net.crossager.krystal.commands;

import net.crossager.krystal.KrystalContext;
import net.crossager.krystal.commandmanager.KrystalCommand;
import net.crossager.krystal.guild.GuildProfile;
import net.crossager.krystal.user.GuildUserProfile;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.Collection;
import java.util.List;

public class PayCommand implements KrystalCommand {
    @Override
    public void onCommand(SlashCommandInteraction command, KrystalContext context, GuildProfile guildProfile) {
        GuildUserProfile from = guildProfile.profiles().get(command.getUser());
        GuildUserProfile to = guildProfile.profiles().get(command.getOption("user").getAsUser().getIdLong());
        long amount = command.getOption("amount").getAsLong();

        if (amount > from.getMoney()) {
            command.replyEmbeds(context.utilities().error(command.getUser(), "You only have " + from.getMoney())).queue();
            return;
        }

        if (to == from) {
            command.replyEmbeds(context.utilities().error(command.getUser(), "You can't give money to yourself")).queue();
            return;
        }

        if (command.getOption("user").getAsUser().isBot()) {
            command.replyEmbeds(context.utilities().error(command.getUser(), "You can't give money to a bot")).queue();
            return;
        }

        from.addMoney(-amount);
        to.addMoney(amount);

        command.replyEmbeds(context.utilities().newCommandEmbed(command.getUser())
                .addField("Transfer complete", "You gave " + amount + " to " + command.getOption("user").getAsUser().getAsMention(), false).build()).queue();
    }

    @Override
    public String name() {
        return "pay";
    }



    @Override
    public Collection<OptionData> options() {
        return List.of(new OptionData(OptionType.INTEGER, "amount", "The amount to give", true).setMinValue(1),
                new OptionData(OptionType.USER, "user", "The user to give money to", true));
    }

    @Override
    public String description() {
        return "Give someone money";
    }
}
