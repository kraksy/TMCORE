package krek.tMCORE.weapons;

import krek.tMCORE.weapons.abilities.WeaponAbility;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import krek.tMCORE.TMCORE;
import krek.tMCORE.Statistics.PlayerStats;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class CustomWeapon {

    private String name;
    private List<Component> lore;
    private Material itemVisual;
    private WeaponAbility ability;
    private PlayerStats reqStats;

    public CustomWeapon(String name, List<Component> lore, Material itemVisual, WeaponAbility ability, PlayerStats reqStats) {
        this.name = name;
        this.lore = lore;
        this.itemVisual = itemVisual;
        this.ability = ability;
        this.reqStats = reqStats;
    }

    public void activate(Player player)
    {
        ability.activate(player);
    }

    public boolean levelReqMet(Player player)
    {
        PlayerStats stats = TMCORE.getPlayerStats(player);
        return stats.getLevel() == reqStats.getLevel();
    }

    public ItemStack createItemStack()
    {
        ItemStack item = new ItemStack(itemVisual);
        ItemMeta meta = item.getItemMeta();
        if (meta != null)
        {
            meta.customName(Component.text(name));
            meta.lore(lore);
            item.setItemMeta(meta);

        }
        item.setAmount(1);
        return item;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Component> getLore() {
        return lore;
    }

    public void setLore(List<Component> lore) {
        this.lore = lore;
    }

    public Material getItemVisual() {
        return itemVisual;
    }

    public void setItemVisual(Material itemVisual) {
        this.itemVisual = itemVisual;
    }

    public WeaponAbility getAbility() {
        return ability;
    }

    public void setAbility(WeaponAbility ability) {
        this.ability = ability;
    }

    public PlayerStats getReqStats() {
        return reqStats;
    }

    public void setReqStats(PlayerStats reqStats) {
        this.reqStats = reqStats;
    }
}
