package net.crossager.krystal.commandmanager;

import net.crossager.krystal.commands.BalanceCommand;
import net.crossager.krystal.commands.HelpCommand;
import net.crossager.krystal.commands.LeaderboardCommand;
import net.crossager.krystal.commands.PayCommand;

import java.util.AbstractList;
import java.util.List;

public class DefaultKrystalCommands extends AbstractList<KrystalCommand> {
    private final List<KrystalCommand> commands = List.of(
            new HelpCommand(),
            new BalanceCommand(),
            new PayCommand(),
            new LeaderboardCommand()
    );

    @Override
    public KrystalCommand get(int index) {
        return commands.get(index);
    }

    @Override
    public int size() {
        return commands.size();
    }
}
