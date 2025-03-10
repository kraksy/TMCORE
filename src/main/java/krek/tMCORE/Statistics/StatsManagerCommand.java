package krek.tMCORE.Statistics;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import static org.bukkit.Bukkit.createInventory;

public class StatsManagerCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {

        if (commandSender instanceof Player e)
        {
            ItemStack levelUp = new ItemStack(Material.GOLD_BLOCK);
            ItemStack levelDown = new ItemStack(Material.GOLD_BLOCK);
            ItemStack vigorUp = new ItemStack(Material.RED_WOOL);
            ItemStack vigorDown = new ItemStack(Material.RED_WOOL);
            ItemStack strengthUp = new ItemStack(Material.IRON_SWORD);
            ItemStack strengthDown = new ItemStack(Material.IRON_SWORD);
            ItemStack dexterityUp = new ItemStack(Material.ARROW);
            ItemStack dexterityDown = new ItemStack(Material.ARROW);
            ItemStack intelligenceUp = new  ItemStack(Material.ENDER_PEARL);
            ItemStack intelligenceDown = new  ItemStack(Material.ENDER_PEARL);

            Inventory inv = createInventory(e, 54, Component.text("stat manager"));

            inv.setItem(0, levelUp);
            inv.setItem(1, levelDown);
            inv.setItem(2, vigorUp);
            inv.setItem(3, vigorDown);
            inv.setItem(4, strengthUp);
            inv.setItem(5, strengthDown);
            inv.setItem(6, dexterityUp);
            inv.setItem(7, dexterityDown);
            inv.setItem(8, intelligenceUp);
            inv.setItem(9, intelligenceDown);

            e.openInventory(inv);
        }
        return true;
    }
}
