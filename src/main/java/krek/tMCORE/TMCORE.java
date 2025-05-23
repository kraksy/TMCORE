package krek.tMCORE;

import krek.tMCORE.HealthBar.EnemyBarManager;
import krek.tMCORE.HealthBar.PlayerBarManager;
import krek.tMCORE.Statistics.PlayerStats;
import krek.tMCORE.Statistics.StatsManagerCommand;
import krek.tMCORE.Utils.Database;
import krek.tMCORE.commands.SpawningMenuCommand;
import krek.tMCORE.commands.SpawningMenuListener;
import krek.tMCORE.weapons.WeaponManager;
import krek.tMCORE.weapons.WeaponSpawnMenu;
import krek.tMCORE.weapons.WeaponSpawnMenuEvents;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import javax.xml.crypto.Data;

import static org.bukkit.Bukkit.createInventory;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;
import java.util.logging.Logger;

import static org.bukkit.Bukkit.createInventory;

public final class TMCORE extends JavaPlugin implements Listener {

    private final Logger log = getLogger();
    static private File playerDataFile;
    static private FileConfiguration playerDataConfig;
    private Database database;

    @Override
    public void onEnable() {
        try {
            if (!getDataFolder().exists()) {
                getDataFolder().mkdir();
            }

            database = new Database(getDataFolder().getAbsoluteFile() + "/TMCORE.db");
        }catch (SQLException | IOException ex)
        {
            ex.printStackTrace();
            System.out.println("failed to connect to database" + ex.getMessage());
            Bukkit.getPluginManager().disablePlugin(this);
        }
        startupMessage();
        createPlayerDataFile();
        registerEvents();
        registerCommands();
    }

    @Override
    public void onDisable() {
        try {
            database.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
        Bukkit.getPluginManager().registerEvents(new WeaponManager(), this);
        Bukkit.getPluginManager().registerEvents(new WeaponSpawnMenuEvents(), this);
        log.info("[TMCORE] Plugin events has been registered");
    }

    public void registerCommands()
    {
        Objects.requireNonNull(getCommand("spawnmenu")).setExecutor(new SpawningMenuCommand());
        Objects.requireNonNull(getCommand("weaponmenu")).setExecutor(new WeaponSpawnMenu());
        Objects.requireNonNull(getCommand("statmenu")).setExecutor(new StatsManagerCommand());
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

    static public PlayerStats getPlayerStats(Player player) {
        PlayerStats stats = new PlayerStats();

        stats.setLevel(playerDataConfig.getInt("players." + player.getUniqueId() + ".level"));
        stats.setXp(playerDataConfig.getInt("players." + player.getUniqueId() + ".xp"));
        stats.setVigor(playerDataConfig.getInt("players." + player.getUniqueId() + ".level"));
        stats.setStrength(playerDataConfig.getInt("players." + player.getUniqueId() + ".level"));
        stats.setDexterity(playerDataConfig.getInt("players." + player.getUniqueId() + ".level"));
        stats.setIntelligence(playerDataConfig.getInt("players." + player.getUniqueId() + ".level"));

        return stats;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) throws SQLException {
        Player player = event.getPlayer();
        if (!player.hasPlayedBefore()) {
            database.addPlayer(player);
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        setPlayerStats(event.getPlayer(), getPlayerStats(event.getPlayer()));
    }


    public void openLevelUpMenu(Player player)
    {
        Inventory inv = createInventory(player, 54, Component.text("Level up!"));
        ItemStack vitality = new ItemStack(Material.RED_WOOL, 1);
        ItemStack strength = new ItemStack(Material.IRON_SWORD, 1);
        ItemStack dexterity = new ItemStack(Material.LEATHER_BOOTS, 1);
        ItemStack intelligence = new ItemStack(Material.ENDER_PEARL, 1);
        inv.setItem(11, vitality);
        inv.setItem(13, strength);
        inv.setItem(15, dexterity);
        inv.setItem(17, intelligence);
        player.openInventory(inv);
    }

    // get xp from mobs and level up
    @EventHandler
    public void onEntityKilled(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player player)
        {
            Entity mob = event.getEntity();
            if (mob instanceof Monster)
            {
                gainXp(player, (int) event.getDamage()); // lmao this is kinda funny
                checkLevelUp(player);
            }
        }
    }

    // levelUP menu will actually level up the player
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player p = (Player) event.getWhoClicked();
        if (event.getView().title().toString().contains("Level up!"))
        {
            event.setCancelled(true);
            PlayerStats stats = getPlayerStats(p);

            ItemStack clickedItem = event.getCurrentItem();
            if (clickedItem == null || clickedItem.getType() == Material.AIR) return;

            // decorate this later with sounds or something
            switch (clickedItem.getType())
            {
                case RED_WOOL:
                    stats.setVigor(stats.getVigor() + 1);
                    gainLevelUp(p);
                    event.getView().close();
                case IRON_SWORD:
                    stats.setStrength(stats.getStrength() + 1);
                    gainLevelUp(p);
                    event.getView().close();
                case LEATHER_BOOTS:
                    stats.setDexterity(stats.getDexterity() + 1);
                    gainLevelUp(p);
                    event.getView().close();
                case ENDER_PEARL:
                    stats.setIntelligence(stats.getIntelligence() + 1);
                    gainLevelUp(p);
                    event.getView().close();
            }

        }
    }

    public void gainXp(Player player, int xp)
    {
        PlayerStats stats = getPlayerStats(player);
        stats.setXp(xp);
        setPlayerStats(player, stats);
    }

    public void checkLevelUp(Player player)
    {
        PlayerStats stats = getPlayerStats(player);
        int xp = stats.getXp();
        for (int i = 0; i < stats.getLevel() ; i++)
        {
            int levelUpReq = i * 20;

            if (xp ==  levelUpReq)
            {
                player.sendMessage("Level up!");
                openLevelUpMenu(player);
            }
        }
    };

    public void gainLevelUp(Player player)
    {
        PlayerStats stats = getPlayerStats(player);
        stats.setLevel(stats.getLevel() +1);
    }
}
