package net.crossager.krystal.user;

import net.dv8tion.jda.api.interactions.components.ComponentInteraction;

public interface UserInteractable {
    boolean onComponentInteraction(ComponentInteraction interaction);
    boolean isAutoCloseable();
    void close();
}
