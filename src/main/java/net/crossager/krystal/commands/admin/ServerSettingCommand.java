package net.crossager.krystal.commands.admin;

import net.crossager.krystal.KrystalContext;
import net.crossager.krystal.commandmanager.KrystalCommand;
import net.crossager.krystal.commandmanager.SubCommandedKrystalCommand;
import net.crossager.krystal.guild.GuildProfile;
import net.crossager.krystal.guild.settings.GuildSetting;
import net.crossager.krystal.guild.settings.ParseException;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.Collection;
import java.util.List;

public class ServerSettingCommand extends SubCommandedKrystalCommand {
    private final GuildSetting<?> setting;

    public ServerSettingCommand(GuildSetting<?> setting) {
        this.setting = setting;
    }

    @Override
    public String name() {
        return setting.name();
    }

    @Override
    public String description() {
        return setting.description();
    }

    @Override
    public Collection<KrystalCommand> subCommands() {
        return List.of(new Set(), new Get());
    }

    private class Set implements KrystalCommand {
        @Override
        public void onCommand(SlashCommandInteraction command, KrystalContext context, GuildProfile guildProfile) {
            OptionMapping option = command.getOption(setting.input().getName());
            try {
                setting.parse(option);
                command.replyEmbeds(guildProfile.newCommandEmbed(command.getUser())
                        .addField("Changed " + setting.name(), setting.name() + " is now " + setting.toStringRepresentation(), false).build()).queue();
            } catch (ParseException e) {
                command.replyEmbeds(context.utilities().error(command.getUser(),
                        "Failed to parse " + option.getAsString())).queue();
            }
        }

        @Override
        public String name() {
            return "set";
        }

        @Override
        public String description() {
            return "Sets the " + setting.name();
        }

        @Override
        public Collection<OptionData> options() {
            return List.of(setting.input());
        }
    }

    private class Get implements KrystalCommand {
        @Override
        public void onCommand(SlashCommandInteraction command, KrystalContext context, GuildProfile guildProfile) {
            command.replyEmbeds(guildProfile.newCommandEmbed(command.getUser())
                    .addField(setting.name(), setting.toStringRepresentation(), false).build()).queue();
        }

        @Override
        public String name() {
            return "get";
        }

        @Override
        public String description() {
            return "Gets the " + setting.name();
        }

        @Override
        public Collection<OptionData> options() {
            return List.of();
        }
    }
}
