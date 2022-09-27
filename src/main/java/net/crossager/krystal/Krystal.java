package net.crossager.krystal;

import net.crossager.krystal.startup.GuildJoinListener;
import net.crossager.krystal.startup.GuildSetup;
import net.crossager.krystal.utils.ResourceAsString;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.SubcommandData;
import net.dv8tion.jda.api.interactions.commands.build.SubcommandGroupData;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.MemberCachePolicy;

public class Krystal {
    private Krystal() throws InterruptedException {
        GuildJoinListener guildJoinListener = new GuildJoinListener();
        JDA jda = JDABuilder.createDefault(new ResourceAsString("/tokens/token").string())
                .enableIntents(GatewayIntent.getIntents(GatewayIntent.ALL_INTENTS))
                .setMemberCachePolicy(MemberCachePolicy.ALL)
                .addEventListeners(guildJoinListener)
                .setActivity(Activity.competing("top leaderboard"))
                .build().awaitReady();
        KrystalContext context = new KrystalContext(jda);
        context.logger().info("Initializing");
        guildJoinListener.setContext(context);
        new GuildSetup(jda.getGuildById(952393176667291729L), context);
    }

    public static void main(String[] args) throws InterruptedException {
        new Krystal();
    }
}
