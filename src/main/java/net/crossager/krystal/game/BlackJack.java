package net.crossager.krystal.game;

import net.crossager.krystal.user.UserInteractable;
import net.dv8tion.jda.api.interactions.components.ComponentInteraction;

public class BlackJack implements UserInteractable {
    @Override
    public boolean onComponentInteraction(ComponentInteraction interaction) {
        return false;
    }

    @Override
    public boolean isAutoCloseable() {
        return false;
    }

    @Override
    public void close() {

    }
}
