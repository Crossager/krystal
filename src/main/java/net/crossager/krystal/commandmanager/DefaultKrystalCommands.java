package net.crossager.krystal.commandmanager;

import net.crossager.krystal.commandmanager.KrystalCommand;
import net.crossager.krystal.commands.HelpCommand;
import net.crossager.krystal.commands.ServerSettingsCommand;

import java.util.AbstractList;
import java.util.List;

public class DefaultKrystalCommands extends AbstractList<KrystalCommand> {
    private final List<KrystalCommand> commands = List.of(
            new HelpCommand(),
            new BalanceCommand(),
            new PayCommand()
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
