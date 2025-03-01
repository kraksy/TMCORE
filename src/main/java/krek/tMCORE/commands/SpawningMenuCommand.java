package krek.tMCORE.commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import static org.bukkit.Bukkit.createInventory;

public class SpawningMenuCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {

        if (commandSender instanceof Player e)
        {
            ItemStack zombie = new ItemStack(Material.ZOMBIE_HEAD);
            Inventory inventory = createInventory(e, 54, Component.text("spawning menu"));
            inventory.setItem(0, zombie);

            e.openInventory(inventory);

        }
        else
        {
            commandSender.sendMessage( NamedTextColor.RED + "You must be a player to use this command!");
        }
        return true;
    }

}
