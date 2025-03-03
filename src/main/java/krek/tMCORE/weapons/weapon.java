package krek.tMCORE.weapons;

import org.bukkit.inventory.ItemStack;

public class weapon {
    int level;
    String name;
    String description;
    ItemStack item;

    public weapon(int level, String name, String description, ItemStack item) {
        this.level = level;
        this.name = name;
        this.description = description;
        this.item = item;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ItemStack getItem() {
        return item;
    }

    public void setItem(ItemStack item) {
        this.item = item;
    }
}
