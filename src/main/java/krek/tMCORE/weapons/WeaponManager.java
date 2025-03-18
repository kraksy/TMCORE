package krek.tMCORE.weapons;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

public class WeaponManager implements Listener {

    private boolean isCrouching = false;
    
    // ability activation event 
    @EventHandler
    public void onPlayerCrouch(PlayerToggleSneakEvent event)
    {
      Player player = event.getPlayer();
      isCrouching = event.isSneaking();
    }

    /*
    @EventHandler
    public void onPlayerRightClick(PlayerInteractEvent event)
    {
      Player player = event.getPlaye();

      switch (event.getItem()) {
        case :
          
          break;

        default:
          break;
      }

      // put this in switch
      if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)
      {
        if (isCrouching)
        {
          
        }

      }
    }
    */

    @EventHandler
    public void onItemDamage(PlayerItemDamageEvent event)
    {
        ItemStack item =  event.getPlayer().getEquipment().getItemInMainHand();
        Material mat = event.getPlayer().getEquipment().getItemInMainHand().getType();

        if (isWeapon(mat))
        {
            event.setCancelled(true);
            repairItem(item);
        }
    }

    private void repairItem(ItemStack item) {
        ItemMeta meta = item.getItemMeta();
        if (meta instanceof Damageable) {
            Damageable damageable = (Damageable) meta;
            damageable.setDamage(0); // Reset the damage
            item.setItemMeta(meta); // Apply the changes
        }
    }

    private boolean isWeapon(Material material) {
        // You can use Material tags if available in your version for cleaner code.
        return material.toString().endsWith("_SWORD") ||
                material.toString().endsWith("_AXE") ||
                material.toString().endsWith("_HOE") ||
                material.toString().endsWith("_SHOVEL") ||
                material.toString().endsWith("_PICKAXE") ||
                material == Material.BOW ||
                material == Material.CROSSBOW ||
                material == Material.TRIDENT;
    }
}
