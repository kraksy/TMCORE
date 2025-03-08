package krek.tMCORE;

import krek.tMCORE.HealthBar.EnemyBarManager;
import krek.tMCORE.HealthBar.PlayerBarManager;
import krek.tMCORE.Statistics.PlayerStats;
import krek.tMCORE.commands.SpawningMenuCommand;
import krek.tMCORE.commands.SpawningMenuListener;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
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

    public void setPlayerStats(Player player, PlayerStats stats) {
        playerDataConfig.set("players." + player.getUniqueId() + ".level", stats.getLevel());
        playerDataConfig.set("players." + player.getUniqueId() + ".xp", stats.getXp());
        playerDataConfig.set("players." + player.getUniqueId() + ".vigor", stats.getVigor());
        playerDataConfig.set("players." + player.getUniqueId() + ".strength" , stats.getStrength());
        playerDataConfig.set("players." + player.getUniqueId() + ".dexterity", stats.getDexterity());
        playerDataConfig.set("players." + player.getUniqueId() +  ".intelligence", stats.getIntelligence());
        savePlayerData();
    }

    public PlayerStats getPlayerStats(Player player) {
        PlayerStats stats = new PlayerStats(0 ,0 , 0, 0, 0, 0);

        stats.setLevel(playerDataConfig.getInt("players." + player.getUniqueId() + ".level"));
        stats.setXp(playerDataConfig.getInt("players." + player.getUniqueId() + ".xp"));
        stats.setVigor(playerDataConfig.getInt("players." + player.getUniqueId() + ".level"));
        stats.setStrength(playerDataConfig.getInt("players." + player.getUniqueId() + ".level"));
        stats.setDexterity(playerDataConfig.getInt("players." + player.getUniqueId() + ".level"));
        stats.setIntelligence(playerDataConfig.getInt("players." + player.getUniqueId() + ".level"));

        return stats;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.sendMessage("Welcome back! Your stats are " + getPlayerStats(player));
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        setPlayerStats(event.getPlayer(), getPlayerStats(event.getPlayer()));
    }

    public boolean checkLevelUp(Player player)
    {
        PlayerStats stats = getPlayerStats(player);
        for (int i = 0; i < stats.getLevel() ; i++)
        {
            int levelUpReq = i * 20;
        }
    };

    // get xp from mobs and level up
    @EventHandler
    public void onEntityKilled(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player player)
        {
            Entity mob = event.getEntity();
            if (mob instanceof Monster)
            {
                // give player xp
                // check for level up
            }
            else
            {

            }
        }
    }


}