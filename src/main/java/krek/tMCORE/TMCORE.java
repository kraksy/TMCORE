package krek.tMCORE;

import krek.tMCORE.HealthBar.EnemyBarManager;
import krek.tMCORE.HealthBar.PlayerBarManager;
import krek.tMCORE.Statistics.PlayerStatsManager;
import krek.tMCORE.commands.SpawningMenuCommand;
import krek.tMCORE.commands.SpawningMenuListener;
import krek.tMCORE.utils.PlayerDataManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;
import java.util.logging.Logger;

public final class TMCORE extends JavaPlugin implements Listener {

    Logger log = getLogger();
    PlayerDataManager playerDataManager = new PlayerDataManager();

    @Override
    public void onEnable() {
        log.info("==============");
        log.info("<< starting >>");
        log.info("==============");

        // playerDataManager.createPlayerDataFile();

        Bukkit.getPluginManager().registerEvents(this, this);
        Bukkit.getPluginManager().registerEvents(new EnemyBarManager(), this);
        log.info("loaded enemy bars");
        Bukkit.getPluginManager().registerEvents(new PlayerBarManager(), this);
        log.info("loaded player bars");

        Bukkit.getPluginManager().registerEvents(new SpawningMenuListener(), this);
        Objects.requireNonNull(getCommand("spawnmenu")).setExecutor(new SpawningMenuCommand());
        log.info("loaded spawning menu");

    }

        /*
        @Override
        public void onDisable() {
            log.info("ending");
            playerDataManager.savePlayerData();
        }

        @EventHandler
        public void onPlayerJoin(PlayerJoinEvent event) {
            Player player = event.getPlayer();
            int level = playerDataManager.getPlayerLevel(player);
            player.sendMessage("Welcome back! Your level is " + level);
        }

        @EventHandler
        public void onPlayerQuit(PlayerQuitEvent event) {
            Player player = event.getPlayer();
            int currentLevel = playerDataManager.getPlayerLevel(player);
            playerDataManager.setPlayerLevel(player, currentLevel);
        }
        */


}
