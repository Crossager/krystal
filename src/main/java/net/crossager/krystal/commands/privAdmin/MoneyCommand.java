package net.crossager.krystal.commands.privAdmin;

import net.crossager.krystal.KrystalContext;
import net.crossager.krystal.commandmanager.KrystalCommand;
import net.crossager.krystal.commandmanager.SubCommandedKrystalCommand;
import net.crossager.krystal.guild.GuildProfile;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.Collection;
import java.util.List;

public class MoneyCommand extends SubCommandedKrystalCommand {
    @Override
    public String name() {
        return "money";
    }

    @Override
    public String description() {
        return "Manages users' money";
    }

    @Override
    public Collection<KrystalCommand> subCommands() {
        return List.of(new Add(), new Remove(), new Set());
    }

    private static class Add implements KrystalCommand {
        @Override
        public void onCommand(SlashCommandInteraction command, KrystalContext context, GuildProfile guildProfile) {
            if (context.utilities().sendAndReturn(
                    !command.getMember().hasPermission(Permission.MANAGE_SERVER),
                    () -> command.replyEmbeds(context.utilities().invalidPermission(Permission.MANAGE_SERVER)).setEphemeral(true).queue()
                    )) return;
            User user = command.getOption("user").getAsUser();
            if (context.utilities().sendAndReturn(
                    user.isBot(),
                    () -> command.replyEmbeds(context.utilities().error(command.getUser(), "Provided user is a bot")).setEphemeral(true).queue()
            )) return;
            long amount = command.getOption("amount").getAsLong();
            EmbedBuilder builder = context.utilities().newCommandEmbed(command.getUser());

            builder.addField("Addition completed", "Added %s to %s".formatted(amount, user.getAsMention()), false);

            guildProfile.profiles().get(user).addMoney(amount);
            command.replyEmbeds(builder.build()).queue();
        }

        @Override
        public String name() {
            return "add";
        }

        @Override
        public String description() {
            return "Adds money to a user";
        }

        @Override
        public Collection<OptionData> options() {
            return List.of(new OptionData(OptionType.USER, "user", "The user", true), new OptionData(OptionType.INTEGER, "amount", "The amount", true));
        }
    }

    private static class Set implements KrystalCommand{
        @Override
        public void onCommand(SlashCommandInteraction command, KrystalContext context, GuildProfile guildProfile) {
            if (context.utilities().sendAndReturn(
                    !command.getMember().hasPermission(Permission.MANAGE_SERVER),
                    () -> command.replyEmbeds(context.utilities().invalidPermission(Permission.MANAGE_SERVER)).setEphemeral(true).queue()
            )) return;
            User user = command.getOption("user").getAsUser();
            if (context.utilities().sendAndReturn(
                    user.isBot(),
                    () -> command.replyEmbeds(context.utilities().error(command.getUser(), "Provided user is a bot")).setEphemeral(true).queue()
            )) return;
            long amount = command.getOption("amount").getAsLong();
            EmbedBuilder builder = context.utilities().newCommandEmbed(command.getUser());

            builder.addField("Set completed", "Set %s for %s".formatted(amount, user.getAsMention()), false);

            guildProfile.profiles().get(user).setMoney(amount);
            command.replyEmbeds(builder.build()).queue();
        }

        @Override
        public String name() {
            return "set";
        }

        @Override
        public String description() {
            return "sets a users money";
        }

        @Override
        public Collection<OptionData> options() {
            return List.of(new OptionData(OptionType.USER, "user", "The user", true), new OptionData(OptionType.INTEGER, "amount", "The amount", true));
        }
    }

    private static class Remove implements KrystalCommand {
        @Override
        public void onCommand(SlashCommandInteraction command, KrystalContext context, GuildProfile guildProfile) {
            if (context.utilities().sendAndReturn(
                    !command.getMember().hasPermission(Permission.MANAGE_SERVER),
                    () -> command.replyEmbeds(context.utilities().invalidPermission(Permission.MANAGE_SERVER)).setEphemeral(true).queue()
            )) return;
            User user = command.getOption("user").getAsUser();
            if (context.utilities().sendAndReturn(
                    user.isBot(),
                    () -> command.replyEmbeds(context.utilities().error(command.getUser(), "Provided user is a bot")).setEphemeral(true).queue()
            )) return;
            long amount = command.getOption("amount").getAsLong();
            EmbedBuilder builder = context.utilities().newCommandEmbed(command.getUser());

            builder.addField("Subtraction completed", "Removed %s from %s".formatted(amount, user.getAsMention()), false);

            guildProfile.profiles().get(user).removeMoney(amount);
            command.replyEmbeds(builder.build()).queue();
        }

        @Override
        public String name() {
            return "remove";
        }

        @Override
        public String description() {
            return "Removes money from users account";
        }

        @Override
        public Collection<OptionData> options() {
            return List.of(new OptionData(OptionType.USER, "user", "The user", true), new OptionData(OptionType.INTEGER, "amount", "The amount", true));
        }
    }
}
