package net.crossager.krystal.commands.admin;

import net.crossager.krystal.KrystalContext;
import net.crossager.krystal.commandmanager.GroupSubCommandedKrystalCommand;
import net.crossager.krystal.commandmanager.KrystalCommand;
import net.crossager.krystal.commandmanager.SubCommandedKrystalCommand;
import net.crossager.krystal.guild.GuildProfile;
import net.crossager.krystal.guild.settings.GuildSetting;
import net.crossager.krystal.guild.settings.GuildSettings;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ServerSettingsCommand extends GroupSubCommandedKrystalCommand {
    private final GuildSettings settings;

    public ServerSettingsCommand(GuildSettings settings) {
        this.settings = settings;
    }

    @Override
    public String name() {
        return "serversettings";
    }

    @Override
    public String description() {
        return "Change the private server instance settings";
    }

    @Override
    public Collection<KrystalCommand> subCommands() {
        return List.of(new KrystalCommand() {
            @Override
            public void onCommand(SlashCommandInteraction command, KrystalContext context, GuildProfile guildProfile) {

            }

            @Override
            public String name() {
                return "view";
            }

            @Override
            public String description() {
                return "View all server settings";
            }

            @Override
            public Collection<OptionData> options() {
                return List.of();
            }
        });
    }

    @Override
    public Collection<SubCommandedKrystalCommand> subCommandGroups() {
        return settings.allSettings().stream().map(ServerSettingCommand::new).map(SubCommandedKrystalCommand.class::cast).toList();
    }
}
