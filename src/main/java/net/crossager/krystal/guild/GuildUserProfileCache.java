package net.crossager.krystal.guild;

import net.crossager.krystal.user.GuildUserProfile;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;

import java.util.LinkedHashMap;
import java.util.Map;

public class GuildUserProfileCache {
    private final Map<Long, GuildUserProfile> userProfiles = new LinkedHashMap<>();
    private final GuildProfile guild;

    public GuildUserProfileCache(GuildProfile guild) {
        this.guild = guild;
    }

    public GuildUserProfile get(long id) {
        GuildUserProfile profile = userProfiles.get(id);
        if (profile == null) {
            profile = new GuildUserProfile(id, guild);
            userProfiles.put(id, profile);
        }
        return profile;
    }

    public GuildUserProfile get(User user) {
        return get(user.getIdLong());
    }

    public GuildUserProfile get(Member member) {
        return get(member.getIdLong());
    }

    public Map<Long, GuildUserProfile> getUserProfiles() {
        return userProfiles;
    }
}
