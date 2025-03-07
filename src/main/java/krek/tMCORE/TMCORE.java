package krek.tMCORE;

import krek.tMCORE.HealthBar.EnemyBarManager;
import krek.tMCORE.HealthBar.PlayerBarManager;
import krek.tMCORE.commands.SpawningMenuCommand;
import krek.tMCORE.commands.SpawningMenuListener;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;
import java.util.logging.Logger;

public final class TMCORE extends JavaPlugin implements Listener {

    private final Logger log = getLogger();
    private File playerDataFile;
    private FileConfiguration playerDataConfig;

    @Override
    public void onEnable() {
        startupMessage();
        createPlayerDataFile();
        registerEvents();
        registerCommands();
    }

    @Override
    public void onDisable() {
        log.info("ending");
        savePlayerData();
    }

    public void startupMessage()
    {
        log.info("==============");
        log.info("<< starting >>");
        log.info("==============");
    }

    public void registerEvents()
    {
        Bukkit.getPluginManager().registerEvents(this, this);
        Bukkit.getPluginManager().registerEvents(new EnemyBarManager(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerBarManager(), this);
        Bukkit.getPluginManager().registerEvents(new SpawningMenuListener(), this);
        log.info("[TMCORE] Plugin events has been registered");
    }

    public void registerCommands()
    {
        Objects.requireNonNull(getCommand("spawnmenu")).setExecutor(new SpawningMenuCommand());
        log.info("[TMCORE] Plugin commands has been registered");
    }

    private void createPlayerDataFile() {
        File dataFolder = getDataFolder();
        if (!dataFolder.exists() && dataFolder.mkdirs()) {
            log.info("Plugin data folder created.");
        }

        playerDataFile = new File(dataFolder, "playerdata.yml");

        if (!playerDataFile.exists()) {
            try {
                if (playerDataFile.createNewFile()) {
                    log.info("playerdata.yml created successfully.");
                }
            } catch (IOException e) {
                log.severe("Could not create playerdata.yml!");
                e.printStackTrace();
                return;
            }
        }
        playerDataConfig = YamlConfiguration.loadConfiguration(playerDataFile);
    }

    public void savePlayerData() {
        if (playerDataConfig == null || playerDataFile == null) {
            log.severe("Error: playerDataConfig or playerDataFile is null! Skipping save.");
            return;
        }
        try {
            playerDataConfig.save(playerDataFile);
        } catch (IOException e) {
            log.severe("Could not save playerdata.yml!");
            e.printStackTrace();
        }
    }

    public void setPlayerLevel(Player player, int level) {
        playerDataConfig.set("players." + player.getUniqueId() + ".level", level);
        savePlayerData();
    }

    public int getPlayerLevel(Player player) {
        return playerDataConfig.getInt("players." + player.getUniqueId() + ".level", 1);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.sendMessage("Welcome back! Your level is " + getPlayerLevel(player));
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        setPlayerLevel(event.getPlayer(), getPlayerLevel(event.getPlayer()));
    }
}