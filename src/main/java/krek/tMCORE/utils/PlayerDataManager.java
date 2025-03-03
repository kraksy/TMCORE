package krek.tMCORE.utils;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerDataManager implements Listener {

    @EventHandler
    public void onPlayerLogin(PlayerJoinEvent event)
    {
        Player player = event.getPlayer();
    }


    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        int level = getPlayerLevel(player);
        player.sendMessage("Welcome back! Your level is " + level);
    }

    // Event: Save player data when they leave
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        int currentLevel = getPlayerLevel(player);
        setPlayerLevel(player, currentLevel); // Saves level when they leave
    }

    /*

        private File playerDataFile;
    private FileConfiguration playerDataConfig;

    @Override
    public void onEnable() {
        // Register events
        getServer().getPluginManager().registerEvents(this, this);

        // Create the data file
        createPlayerDataFile();
    }

    @Override
    public void onDisable() {
        // Save player data before the plugin shuts down
        savePlayerData();
    }

    private void createPlayerDataFile() {
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
            e.printStackTrace();
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

    // Event: Load player data when they join
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        int level = getPlayerLevel(player);
        player.sendMessage("Welcome back! Your level is " + level);
    }

    // Event: Save player data when they leave
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        int currentLevel = getPlayerLevel(player);
        setPlayerLevel(player, currentLevel); // Saves level when they leave
    }
}
     */


}
