package krek.tMCORE.weapons;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

public class weaponManager implements Listener {

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
