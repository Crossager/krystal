package net.crossager.krystal;

import net.crossager.krystal.startup.GuildJoinListener;
import net.crossager.krystal.startup.GuildSetup;
import net.crossager.krystal.utils.ResourceAsString;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class Krystal {
    private Krystal() throws InterruptedException {
        GuildJoinListener guildJoinListener = new GuildJoinListener();
        JDA jda = JDABuilder.createDefault(new ResourceAsString("/tokens/token").string())
                .enableIntents(GatewayIntent.getIntents(GatewayIntent.ALL_INTENTS))
                .addEventListeners(guildJoinListener)
                .setActivity(Activity.competing("top leaderboard"))
                .build().awaitReady();
//        jda.getGuildById(849265512415494184L).retrieveCommands().complete().forEach(c -> c.delete().queue());
        KrystalContext context = new KrystalContext(jda);
        context.logger().info("Initializing");
        guildJoinListener.setContext(context);
        new GuildSetup(jda.getGuildById(849265512415494184L), context);
    }

    public static void main(String[] args) throws InterruptedException {
        new Krystal();
    }
}
