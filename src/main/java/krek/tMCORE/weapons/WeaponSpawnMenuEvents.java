package krek.tMCORE.weapons;

import krek.tMCORE.Statistics.PlayerStats;
import krek.tMCORE.weapons.abilities.DashAbility;
import krek.tMCORE.weapons.predif.Sword;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class WeaponSpawnMenuEvents implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        Inventory inv = e.getInventory();

        Sword BigSword = new Sword(
                "BigSword",
                List.of(Component.text("its a BigSword, and its getting bigger")),
                Material.IRON_SWORD,
                new DashAbility(),
                new PlayerStats(10, 0, 0, 7, 0, 0),
                false
        );

        if (e.getView().title().toString().contains("Weapons"))
        {
            e.setCancelled(true);
            ItemStack clickedItem = e.getCurrentItem();
            if (clickedItem == null || clickedItem.getType() == Material.AIR) return;

            if (clickedItem.getType() == Material.IRON_SWORD)
            {
                p.give(BigSword.createItemStack());
            }
        }
    }
}