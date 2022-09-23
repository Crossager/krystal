package net.crossager.krystal.commands;

import net.crossager.krystal.KrystalContext;
import net.crossager.krystal.commandmanager.KrystalCommand;
import net.crossager.krystal.commandmanager.SubCommandedKrystalCommand;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.Collection;
import java.util.List;

public class ServerSettingsCommand extends SubCommandedKrystalCommand {
    @Override
    public String name() {
        return "serversettings";
    }

    @Override
    public String description() {
        return "Change the custom server instance settings";
    }

    @Override
    public Collection<KrystalCommand> subCommands() {
        return List.of(new RandomSetting());
    }

    public static class RandomSetting implements KrystalCommand {
        @Override
        public void onCommand(SlashCommandInteraction command, KrystalContext context) {
            command.reply("random").queue();
        }

        @Override
        public String name() {
            return "random";
        }

        @Override
        public String description() {
            return "Random ass description";
        }

        @Override
        public Collection<OptionData> options() {
            return List.of(new OptionData(OptionType.INTEGER, "i", "i", true));
        }
    }
}
