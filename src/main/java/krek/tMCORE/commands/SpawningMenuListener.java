package krek.tMCORE.commands;

import io.papermc.paper.event.entity.EntityMoveEvent;
import krek.tMCORE.TMCORE;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class SpawningMenuListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();

        if (e.getView().title().toString().contains("spawning menu")) {
            e.setCancelled(true);

            ItemStack clickedItem = e.getCurrentItem();
            if (clickedItem == null || clickedItem.getType() == Material.AIR) return;

            if (clickedItem.getType() == Material.ZOMBIE_HEAD) {
                p.sendMessage(Component.text("You clicked the Spawn Zombie egg!", NamedTextColor.GREEN));
                p.getWorld().spawnEntity(p.getLocation(), EntityType.ZOMBIE);
            }
        }
    }

    // mob radius limiting

    /*
    @EventHandler
    public void onEntitySpawn(EntitySpawnEvent e) {
        if (!(e.getEntity() instanceof Mob mob)) return;

        Location spawnLocation = e.getLocation();
        double radius = 5.0;

        new BukkitRunnable() {
            @Override
            public void run() {
                if (mob.isDead()) {
                    cancel();
                    return;
                }

                Location currentLocation = mob.getLocation();
                double distance = spawnLocation.distance(currentLocation);

                if (distance > radius) {
                    Vector direction = spawnLocation.toVector().subtract(currentLocation.toVector()).normalize();
                    mob.setVelocity(direction.multiply(0.3)); // Increased force

                    mob.setTarget(null); // Stops AI from running away

                    if (distance > radius * 2) {
                        mob.teleport(spawnLocation);
                        mob.setVelocity(new Vector(0, 0, 0)); // Prevents continued movement
                    }
                }
            }
        }.runTaskTimer(TMCORE.getPlugin(TMCORE.class), 0L, 5L); // Runs faster (every 5 ticks)
    }

    */

}
