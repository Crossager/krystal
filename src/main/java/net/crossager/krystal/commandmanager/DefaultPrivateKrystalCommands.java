package net.crossager.krystal.commandmanager;

import net.crossager.krystal.commands.ServerSettingsCommand;
import net.crossager.krystal.commands.admin.MoneyCommand;

import java.util.AbstractList;
import java.util.List;

public class DefaultPrivateKrystalCommands extends AbstractList<KrystalCommand> {
    private final List<KrystalCommand> commands = List.of(
            new MoneyCommand(),
            new ServerSettingsCommand()
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
