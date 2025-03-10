package krek.tMCORE.weapons;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;

public class sword extends weapon implements Listener{


  // this shit sounds cool af
  boolean isDualWield;
  boolean isEnchanted;

  public sword(int levelReq, String name, String lore, ItemStack itemVisual,
    boolean isDualWield, boolean isEnchanted
  ) 
  {
    super(levelReq, name, lore, itemVisual);
    this.isDualWield = isDualWield;
    this.isEnchanted = isEnchanted;
  }
    
  @EventHandler
  public boolean checkIfDualWield(PlayerItemHeldEvent event)  
  {
    ItemStack heldItem = event.getPlayer().getActiveItem();
  }

  @EventHandler
  public boolean checkIfEnchanted()
  {}

}
