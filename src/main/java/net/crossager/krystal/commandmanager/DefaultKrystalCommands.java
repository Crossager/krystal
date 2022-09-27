package net.crossager.krystal.commandmanager;

import net.crossager.krystal.commands.*;
import net.crossager.krystal.guild.GuildProfile;

import java.util.AbstractList;
import java.util.List;

public class DefaultKrystalCommands extends AbstractList<KrystalCommand> {
    private final List<KrystalCommand> commands;

    public DefaultKrystalCommands(GuildProfile profile) {
        commands = List.of(
                new HelpCommand(),
                new BalanceCommand(),
                new PayCommand(),
                new LeaderboardCommand(),
                new WorkCommand(List.of())
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
