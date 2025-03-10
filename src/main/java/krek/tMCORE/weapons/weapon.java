package krek.tMCORE.weapons;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import krek.tMCORE.TMCORE;
import krek.tMCORE.Statistics.PlayerStats;

public class weapon {
    
    int levelReq;
    String name;
    String lore;
    ItemStack itemVisual;

    public weapon(int levelReq, String name, String lore, ItemStack itemVisual) {
        this.levelReq = levelReq;
        this.name = name;
        this.lore = lore;
        this.itemVisual = itemVisual;
    }
    
    
    public boolean levelReqMet(Player player)
    {
      int weaponLevel = getLevelReq();
      PlayerStats stats = TMCORE.getPlayerStats(player);

      if (stats.getLevel() == weaponLevel)
      {
        return true;
      }
      return false;
    }

    public int getLevelReq() {
        return levelReq;
    }

    public void setLevelReq(int levelReq) {
        this.levelReq = levelReq;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public String getLore() {
        return lore;
    }

    public void setLore(String lore) {
        this.lore = lore;
    }

    public ItemStack getItemVisual() {
        return itemVisual;
    }

    public void setItemVisual(ItemStack itemVisual) {
        this.itemVisual = itemVisual;
    }
}
