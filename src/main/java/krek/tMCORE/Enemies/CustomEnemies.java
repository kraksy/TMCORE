package krek.tMCORE.Enemies;

import krek.tMCORE.Enemies.abilities.EnemyAbility;
import krek.tMCORE.Statistics.EnemyStats;

public class CustomEnemies {

    private String name;
    private EnemyAbility ability;
    private EnemyStats stats;

    public CustomEnemies(String name, EnemyAbility ability, EnemyStats stats) {
        this.name = name;
        this.ability = ability;
        this.stats = stats;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EnemyAbility getAbility() {
        return ability;
    }

    public void setAbility(EnemyAbility ability) {
        this.ability = ability;
    }

    public EnemyStats getStats() {
        return stats;
    }

    public void setStats(EnemyStats stats) {
        this.stats = stats;
    }
}