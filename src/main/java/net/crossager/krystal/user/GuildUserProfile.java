package net.crossager.krystal.user;

import net.crossager.krystal.guild.GuildProfile;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;

import java.util.Optional;

public class GuildUserProfile {
    private final long id;
    private final GuildProfile guildProfile;
    private UserInteractable userInteractable;
    private final User user;

    private long money;

    public GuildUserProfile(long id, GuildProfile guildProfile) {
        this.id = id;
        this.guildProfile = guildProfile;
        this.user = guildProfile.context().jda().getUserById(id);
    }

    public GuildUserProfile setMoney(long money) {
        this.money = money;
        return this;
    }

    public GuildUserProfile addMoney(long money) {
        this.money += money;
        return this;
    }

    public GuildUserProfile removeMoney(long money){
        this.money -= money;
        return this;
    }

    public void setUserInteractable(UserInteractable userInteractable) {
        this.userInteractable = userInteractable;
    }

    public void removeUserInteractable() {
        userInteractable = null;
    }

    public Optional<UserInteractable> getUserInteractable() {
        return Optional.ofNullable(userInteractable);
    }

    public long getMoney() {
        return money;
    }

    public User getUser() {
        return user;
    }
}
