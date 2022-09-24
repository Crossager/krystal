package net.crossager.krystal.commands;

import net.crossager.krystal.KrystalContext;
import net.crossager.krystal.commandmanager.KrystalCommand;
import net.crossager.krystal.guild.GuildProfile;
import net.crossager.krystal.user.GuildUserProfile;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.Collection;
import java.util.List;

public class BalanceCommand implements KrystalCommand {

    @Override
    public void onCommand(SlashCommandInteraction command, KrystalContext context, GuildProfile guildProfile) {
        User user = command.getUser();
        if (command.getOption("user") != null) {
            user = command.getOption("user").getAsUser();
        }
        EmbedBuilder builder = context.utilities().newCommandEmbed(command.getUser());
        GuildUserProfile profile = guildProfile.profiles().get(user);
        builder.addField("money", String.valueOf(profile.getMoney()), false);
        command.replyEmbeds(builder.build()).queue();
    }

    @Override
    public String name() {
        return "balance";
    }

    @Override
    public String description() {
        return "returns balance of user";
    }

    @Override
    public Collection<OptionData> options() {
        return List.of(new OptionData(OptionType.USER, "user", "The user", false));
    }
}
