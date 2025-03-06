package krek.tMCORE.utils;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import static org.bukkit.Bukkit.getLogger;

public class PlayerDataManager extends JavaPlugin implements Listener {

    public File playerDataFile;
    public FileConfiguration playerDataConfig;

    public void createPlayerDataFile() {
        playerDataFile = new File(getDataFolder(), "playerdata.yml");

        if (!playerDataFile.exists()) {
            saveResource("playerdata.yml", false); // Creates the file if it doesn't exist
        }

        playerDataConfig = YamlConfiguration.loadConfiguration(playerDataFile);
    }

    public void savePlayerData() {
        try {
            playerDataConfig.save(playerDataFile);
        } catch (IOException e) {
            getLogger().severe("Could not save player data file!");
        }
    }

    // Method to set a player's level
    public void setPlayerLevel(Player player, int level) {
        UUID uuid = player.getUniqueId();
        playerDataConfig.set("players." + uuid + ".level", level);
        savePlayerData();
    }

    // Method to get a player's level
    public int getPlayerLevel(Player player) {
        UUID uuid = player.getUniqueId();
        return playerDataConfig.getInt("players." + uuid + ".level", 1); // Default to level 1
    }



}
