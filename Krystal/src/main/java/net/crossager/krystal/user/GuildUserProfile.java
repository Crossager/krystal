package net.crossager.krystal.user;

import net.crossager.krystal.guild.GuildProfile;

public class GuildUserProfile {
    private final long id;
    private final GuildProfile guildProfile;

    private long money;

    public GuildUserProfile(long id, GuildProfile guildProfile) {
        this.id = id;
        this.guildProfile = guildProfile;
    }

    public GuildUserProfile setMoney(long money) {
        this.money = money;
        return this;
    }

    public long getMoney() {
        return money;
    }
}
