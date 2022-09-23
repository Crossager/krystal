package net.crossager.krystal.startup;

import net.crossager.krystal.KrystalContext;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class GuildJoinListener extends ListenerAdapter {
    private KrystalContext context;

    public void setContext(KrystalContext context) {
        this.context = context;
    }

    @Override
    public void onGuildJoin(@NotNull GuildJoinEvent event) {
        // In case the event fires before the bot is set up. Probably wont happen
        if (context == null) {
            event.getGuild().leave().queue();
            return;
        }
        new GuildSetup(event.getGuild(), context);
    }
}
