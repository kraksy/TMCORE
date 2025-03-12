package krek.tMCORE.Statistics;

import krek.tMCORE.TMCORE;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class StatsManagerEvents implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();

        if (e.getView().title().toString().contains("Statistics"))
        {
            e.setCancelled(true);

            ItemStack clickedItem = e.getCurrentItem();
            if (clickedItem == null || clickedItem.getType() == Material.AIR) return;

            PlayerStats stats = TMCORE.getPlayerStats(p);
            switch (e.getCurrentItem().getType())
            {
                case Material.GOLD_BLOCK -> {
                    stats.setLevel(stats.getLevel() + 1);
                    p.sendMessage(Color.GREEN + "Level: " + stats.getLevel());
                }
                case Material.IRON_BLOCK -> {
                    stats.setLevel(stats.getLevel() - 1);
                    p.sendMessage(Color.GREEN + "Level: " + stats.getLevel());
                }
                case Material.RED_WOOL -> {
                    stats.setVigor(stats.getVigor() + 1);
                    p.sendMessage(Color.GREEN + "Vigor: " + stats.getVigor());
                }
                case Material.WHITE_WOOL -> {
                    stats.setVigor(stats.getVigor() - 1);
                    p.sendMessage(Color.GREEN + "Vigor: " + stats.getVigor());
                }
                case Material.IRON_SWORD ->  {
                    stats.setStrength(stats.getStrength() + 1);
                    p.sendMessage(Color.GREEN + "Strength: " + stats.getStrength());
                }
                case Material.GOLDEN_SWORD -> {
                    stats.setStrength(stats.getStrength() - 1);
                    p.sendMessage(Color.GREEN + "Strength: " + stats.getStrength());
                }
                case Material.ARROW -> {
                    stats.setDexterity(stats.getDexterity() + 1);
                    p.sendMessage(Color.GREEN + "Dexterity: " + stats.getDexterity());
                }
                case Material.SPECTRAL_ARROW ->  {
                    stats.setDexterity(stats.getDexterity() - 1);
                    p.sendMessage(Color.GREEN + "Dexterity: " + stats.getDexterity());
                }
                case Material.ENDER_PEARL -> {
                    stats.setIntelligence(stats.getIntelligence() + 1);
                    p.sendMessage(Color.GREEN + "Intelligence: " + stats.getIntelligence());
                }
                case Material.SNOWBALL -> {
                    stats.setIntelligence(stats.getIntelligence() - 1);
                    p.sendMessage(Color.GREEN + "Intelligence: " + stats.getIntelligence());
                }
            }
        }
    }
}
