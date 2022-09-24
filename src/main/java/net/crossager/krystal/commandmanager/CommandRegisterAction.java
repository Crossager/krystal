package net.crossager.krystal.commandmanager;

import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;

import java.util.Collection;
import java.util.List;

public interface CommandRegisterAction extends CommandListUpdateAction {
    CommandRegisterAction addCommand(KrystalCommand command);
    CommandRegisterAction addCommands(List<KrystalCommand> commands);
    CommandRegisterAction addCommands(KrystalCommand... commands);
}
