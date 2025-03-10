package krek.tMCORE.weapons.abilities;

import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class DashAbility extends WeaponAbility{
    @Override
    public void activate(Player player) {
        Vector direction = player.getLocation().getDirection();
        Vector DashVelocity = direction.multiply(1.5);
        player.setVelocity(DashVelocity);
        player.playSound(player.getLocation(), "minecraft:entity.player.burp", 1.0f, 1.5f);
    }
}