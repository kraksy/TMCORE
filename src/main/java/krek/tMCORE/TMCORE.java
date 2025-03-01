package krek.tMCORE;

import krek.tMCORE.HealthBar.EnemyBarManager;
import krek.tMCORE.HealthBar.PlayerBarManager;
import krek.tMCORE.commands.SpawningMenuCommand;
import krek.tMCORE.commands.SpawningMenuListener;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;
import java.util.logging.Logger;

public final class TMCORE extends JavaPlugin implements Listener {

    Logger log = getLogger();

    @Override
    public void onEnable() {
        log.info("==============");
        log.info("<< starting >>");
        log.info("==============");

        Bukkit.getPluginManager().registerEvents(this, this);
        Bukkit.getPluginManager().registerEvents(new EnemyBarManager(), this);
        log.info("loaded enemy bars");
        Bukkit.getPluginManager().registerEvents(new PlayerBarManager(), this);
        log.info("loaded player bars");

        Bukkit.getPluginManager().registerEvents(new SpawningMenuListener(), this);
        Objects.requireNonNull(getCommand("spawnmenu")).setExecutor(new SpawningMenuCommand());
        log.info("loaded spawning menu");

    }

    @Override
    public void onDisable() {
        log.info("ending");
    }
}
