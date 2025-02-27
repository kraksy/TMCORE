package krek.tMCORE;

import krek.tMCORE.HealthBar.EnemyBarManager;
import krek.tMCORE.HealthBar.PlayerBarManager;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

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
    }

    @Override
    public void onDisable() {
        log.info("ending");
    }
}
