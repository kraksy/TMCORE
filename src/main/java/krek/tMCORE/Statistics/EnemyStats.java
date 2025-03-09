package krek.tMCORE.Statistics;

import org.bukkit.entity.Monster;

public class EnemyStats {
    int level;
    int health;
    int damage;
    int speed;
    int armor;

    public EnemyStats(int level, int health, int damage, int speed, int armor) {
        this.level = level;
        this.health = health;
        this.damage = damage;
        this.speed = speed;
        this.armor = armor;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

}