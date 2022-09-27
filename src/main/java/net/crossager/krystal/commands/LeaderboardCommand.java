package net.crossager.krystal.commands;

import net.crossager.krystal.KrystalContext;
import net.crossager.krystal.commandmanager.KrystalCommand;
import net.crossager.krystal.guild.GuildProfile;
import net.crossager.krystal.user.GuildUserProfile;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.*;

public class LeaderboardCommand implements KrystalCommand {
    public static final int MEMBERS_PER_PAGE = 15;

    @Override
    public void onCommand(SlashCommandInteraction command, KrystalContext context, GuildProfile guildProfile) {
        int page = 0;
        if (command.getOption("page") != null){
            page = (int) (command.getOption("page").getAsLong() - 1);
        }
        EmbedBuilder builder = guildProfile.newCommandEmbed(command.getUser());
        StringBuilder s1 = new StringBuilder();
        StringBuilder s2 = new StringBuilder();
        StringBuilder s3 = new StringBuilder();

        List<GuildUserProfile> leaderboard = new ArrayList<>(guildProfile.profiles().getUserProfiles().values());
        leaderboard.sort(Comparator.comparingLong(GuildUserProfile::getMoney));
        Collections.reverse(leaderboard);

        if (leaderboard.isEmpty()) leaderboard.add(guildProfile.profiles().get(command.getUser()));


        for (int i = MEMBERS_PER_PAGE * page; i < MEMBERS_PER_PAGE * page + MEMBERS_PER_PAGE; i++) {
            if (i > leaderboard.size() - 1) break;
            GuildUserProfile profile = leaderboard.get(i);
            s1.append("#").append(i + 1).append("\n");
            s2.append(profile.getUser().getAsTag()).append("\n");
            s3.append("**").append(profile.getMoney()).append("**").append("\n");
        }
        s1.deleteCharAt(s1.length() - 1);
        s2.deleteCharAt(s2.length() - 1);
        s3.deleteCharAt(s3.length() - 1);

        builder.addField("Rank", s1.toString(), true);
        builder.addField("User", s2.toString(), true);
        builder.addField("Money", s3.toString(), true);
        command.replyEmbeds(builder.build()).queue();
    }


    @Override
    public String name() {
        return "leaderboard";
    }

    @Override
    public Collection<OptionData> options() {
        return List.of(new OptionData(OptionType.INTEGER, "page", "The page of the leaderboard", false).setMinValue(1));
    }

    @Override
    public String description() {
        return "Displays the money leaderboard";
    }
}
