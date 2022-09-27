package net.crossager.krystal.commandmanager;

import net.crossager.krystal.commands.admin.ServerSettingsCommand;
import net.crossager.krystal.commands.admin.MoneyCommand;
import net.crossager.krystal.guild.GuildProfile;

import java.util.AbstractList;
import java.util.List;

public class DefaultPrivateKrystalCommands extends AbstractList<KrystalCommand> {
    private final GuildProfile profile;

    private final List<KrystalCommand> commands;
    public DefaultPrivateKrystalCommands(GuildProfile profile) {
        this.profile = profile;
        commands = List.of(
                new MoneyCommand(),
                new ServerSettingsCommand(profile.settings())
        );
    }

    @Override
    public KrystalCommand get(int index) {
        return commands.get(index);
    }

    @Override
    public int size() {
        return commands.size();
    }
}
