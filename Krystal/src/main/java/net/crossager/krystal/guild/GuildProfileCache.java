package net.crossager.krystal.guild;

import net.crossager.krystal.KrystalContext;
import net.dv8tion.jda.api.entities.Guild;

import java.util.LinkedHashMap;
import java.util.Optional;

public class GuildProfileCache extends LinkedHashMap<Long, GuildProfile> {
    private final GuildProfile defaultProfile;
    private final KrystalContext context;

    public GuildProfileCache(GuildProfile defaultProfile, KrystalContext context) {
        this.defaultProfile = defaultProfile;
        this.context = context;
    }

    public Optional<GuildProfile> get(long id) {
        return Optional.ofNullable(super.get(id));
    }

    public Optional<GuildProfile> get(Guild guild) {
        return Optional.ofNullable(super.get(guild.getIdLong()));
    }

    public GuildProfile registerAsDefault(Guild guild) {
        put(guild.getIdLong(), defaultProfile);
        defaultProfile.addGuild(guild);
        return defaultProfile;
    }

    public CustomGuildProfile register(Guild guild) {
        CustomGuildProfile profile = new CustomGuildProfile(guild, context);
        put(guild.getIdLong(), profile);
        return profile;
    }
}
