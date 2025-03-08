package krek.tMCORE.Statistics;

import org.bukkit.entity.Player;

public class PlayerStats {

    int level;
    int xp;
    int vigor;
    int strength;
    int dexterity;
    int intelligence;

    public PlayerStats(int level, int xp, int vigor, int strength, int dexterity, int intelligence) {
        this.level = level;
        this.xp = xp;
        this.vigor = vigor;
        this.strength = strength;
        this.dexterity = dexterity;
        this.intelligence = intelligence;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
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
