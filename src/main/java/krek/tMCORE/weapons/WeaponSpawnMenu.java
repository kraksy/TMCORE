package krek.tMCORE.weapons;

import krek.tMCORE.Statistics.PlayerStats;
import krek.tMCORE.weapons.abilities.DashAbility;
import krek.tMCORE.weapons.predif.Sword;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

import static org.bukkit.Bukkit.createInventory;

public class WeaponSpawnMenu implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {
        if (commandSender instanceof Player e)
        {
            Sword BigSword = new Sword(
                    "BigSword",
                    List.of(Component.text("its a BigSword, and its getting bigger")),
                    Material.IRON_SWORD,
                    new DashAbility(),
                    new PlayerStats(10, 0, 0, 7, 0, 0),
                    false
            );

            Inventory inventory = createInventory(e, 54, Component.text("spawning menu"));
            inventory.setItem(1, BigSword.createItemStack());
            e.openInventory(inventory);
        }
        else
        {
            commandSender.sendMessage( NamedTextColor.RED + "You must be a player to use this command!");
        }
        return true;
    }
}