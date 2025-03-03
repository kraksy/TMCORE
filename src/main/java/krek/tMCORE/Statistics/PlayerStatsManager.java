package krek.tMCORE.Statistics;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class PlayerStatsManager implements Listener {

    public void saveData(Player p) {}

    public void loadData(Player p) {}

    public void updateData(Player p) {}


    @EventHandler
    public void onEnemyKill(EntityDeathEvent e) {
        if (e.getEntity().getKiller() instanceof Player p) {
            FileConfiguration config = getConfig()
        }
    }














}
