package net.crossager.krystal.startup;

import net.crossager.krystal.KrystalContext;
import net.crossager.krystal.commandmanager.DefaultKrystalCommands;
import net.crossager.krystal.commands.ServerSettingsCommand;
import net.crossager.krystal.guild.CustomGuildProfile;
import net.crossager.krystal.guild.GuildProfile;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageBulkDeleteEvent;
import net.dv8tion.jda.api.events.message.MessageDeleteEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;

public class GuildSetup extends ListenerAdapter {
    private final Guild guild;
    private final KrystalContext context;
    private Message message;

    public GuildSetup(Guild guild, KrystalContext context) {
        this.guild = guild;
        this.context = context;
        if (context.guildCache().get(guild).isPresent()) return;
        getAvailableTextChannel(guild.getTextChannels()).ifPresentOrElse(channel -> channel.sendMessageEmbeds(new EmbedBuilder()
                        .setColor(context.getColor())
                        .setDescription("Thank you for using Krystal. Krystal is a large economy bot that can provide tons of entertainment to our users." +
                                "\nBefore we continue, please select which kind of instance you want to bot to run")
                        .addField("Shared instance", "Every server shares the same leaderboard instance, you cant customize it", false)
                        .addField("Private instance", "This applies only to this server, allows you to customize it", false)
                        .build())
                .setActionRow(
                        Button.success("setup-shared", "Shared"),
                        Button.primary("setup-private", "Private")
                ).queue(message -> GuildSetup.this.message = message), guild.leave()::queue);
        context.jda().addEventListener(this);
    }

    private Optional<TextChannel> getAvailableTextChannel(List<TextChannel> textChannels) {
        for (TextChannel textChannel : textChannels) {
            if (textChannel.canTalk()) return Optional.of(textChannel);
        }
        return Optional.empty();
    }

    @Override
    public void onMessageDelete(@NotNull MessageDeleteEvent event) {
        if (message == null || message.getIdLong() != event.getMessageIdLong()) return;
        guild.leave().queue();
    }

    @Override
    public void onMessageBulkDelete(@NotNull MessageBulkDeleteEvent event) {
        if (message == null || event.getMessageIds().contains(message.getId())) return;
        guild.leave().queue();
    }

    @Override
    public void onButtonInteraction(@NotNull ButtonInteractionEvent event) {
        if (!event.isFromGuild()) return;
        if (message == null || message.getIdLong() != event.getMessageIdLong()) return;
        switch (event.getComponentId()) {
            case "setup-shared" -> {
                if (context.utilities().sendAndReturn(
                        !event.getMember().hasPermission(Permission.MANAGE_SERVER),
                        () -> event.replyEmbeds(context.utilities().invalidPermission(Permission.MANAGE_SERVER)).setEphemeral(true).queue())) return;
                Optional<GuildProfile> guildProfile = context.guildCache().get(event.getGuild());
                guildProfile.ifPresent(guild -> {
                    event.reply("This server is already running on an instance").setEphemeral(true).queue();
                    event.getMessage().delete().queue();
                });
                if (guildProfile.isPresent()) return;

                context.guildCache().registerAsDefault(event.getGuild());
                event.editMessageEmbeds(new EmbedBuilder()
                        .setColor(context.getColor())
                        .addField("Shared instance", "The bot is up and running using the `shared` instance", false)
                        .build()).setComponents().queue();
            }
            case "setup-private" -> {
                if (context.utilities().sendAndReturn(
                        !event.getMember().hasPermission(Permission.MANAGE_SERVER),
                        () -> event.replyEmbeds(context.utilities().invalidPermission(Permission.MANAGE_SERVER)).setEphemeral(true).queue())) return;
                Optional<GuildProfile> guildProfile = context.guildCache().get(event.getIdLong());
                guildProfile.ifPresent(guild -> {
                    event.reply("This server is already running on an instance").setEphemeral(true).queue();
                    event.getMessage().delete().queue();
                });
                if (guildProfile.isPresent()) return;

                CustomGuildProfile profile = context.guildCache().register(event.getGuild());
                profile.commands().registerCommands(new DefaultKrystalCommands());
                profile.commands().registerCommands(List.of(new ServerSettingsCommand()));
                event.editMessageEmbeds(new EmbedBuilder()
                        .setColor(context.getColor())
                        .addField("Private instance", "The bot is up and running using a `private` instance", false)
                        .build()).setComponents().queue();
            }
        }
    }
}
