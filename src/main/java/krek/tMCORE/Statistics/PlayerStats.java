package krek.tMCORE.Statistics;

import org.bukkit.entity.Player;

public class PlayerStats {

    Player player;
    int level;

    int vigor;
    int strength;
    int dexterity;
    int intelligence;

    public PlayerStats(Player player, int level, int vigor, int strength, int dexterity, int intelligence) {
        this.player = player;
        this.level = level;
        this.vigor = vigor;
        this.strength = strength;
        this.dexterity = dexterity;
        this.intelligence = intelligence;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getVigor() {
        return vigor;
    }

    public void setVigor(int vigor) {
        this.vigor = vigor;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getDexterity() {
        return dexterity;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }
}
