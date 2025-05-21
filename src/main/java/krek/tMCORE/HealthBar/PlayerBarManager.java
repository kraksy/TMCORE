package krek.tMCORE.HealthBar;

import krek.tMCORE.TMCORE;
import krek.tMCORE.Utils.NumberUtils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Objects;


public class PlayerBarManager implements Listener {
    Bar playerBar = new Bar(
            "█",
            "-",
            "[",
            "]",
            "♥",
            "♦",
            "♣",
            0,
            0,
            "0",
            "0",
            0
            );


    public Component barAssemble() {
        return  Component.text(playerBar.leftCorner, NamedTextColor.GREEN)
                .append(Component.text(playerBar.HV, NamedTextColor.RED))
                .append(Component.text(playerBar.HIcon, NamedTextColor.DARK_RED))
                .append(Component.text(playerBar.rightCorner, NamedTextColor.GREEN))
                .append(Component.text("   ")) // spacing
                .append(Component.text(playerBar.leftCorner, NamedTextColor.BLUE))
                .append(Component.text(playerBar.MV, NamedTextColor.AQUA))
                .append(Component.text(playerBar.MIcon, NamedTextColor.DARK_AQUA))
                .append(Component.text(playerBar.rightCorner, NamedTextColor.BLUE))
                .append(Component.text("   ")) // spacing
                .append(Component.text(playerBar.leftCorner, NamedTextColor.GRAY))
                .append(Component.text(playerBar.AV, NamedTextColor.WHITE))
                .append(Component.text(playerBar.AIcon, NamedTextColor.DARK_GRAY))
                .append(Component.text(playerBar.rightCorner, NamedTextColor.GRAY));
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event)
    {
        Player player = event.getPlayer();

        AttributeInstance healthAttr = player.getAttribute(Attribute.MAX_HEALTH);
        AttributeInstance armorAttr = player.getAttribute(Attribute.ARMOR);

        new BukkitRunnable() {
            @Override
            public void run() {
                if (healthAttr != null) playerBar.HV = healthAttr.getValue();
                if (armorAttr != null) playerBar.AV = armorAttr.getValue();

                if (!player.isOnline()) {
                    this.cancel(); // Stop the task if the player is offline
                    return;
                }
                player.sendActionBar(barAssemble());
            }
        }.runTaskTimer(TMCORE.getPlugin(TMCORE.class), 0L, 5L);

    }

    // below is prob not important
    @EventHandler
    public void onPlayerHeal(EntityRegainHealthEvent event)
    {
        if (event.getEntity() instanceof Player p)
        {
            playerBar.HV = NumberUtils.format(p.getHealth());
            playerBar.AV = Objects.requireNonNull(p.getAttribute(Attribute.ARMOR)).getValue();
            p.sendActionBar(barAssemble());
        }
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageByEntityEvent event)
    {
        if (event.getEntity() instanceof Player p) {
            playerBar.HV = NumberUtils.format(p.getHealth());
            playerBar.AV = Objects.requireNonNull(p.getAttribute(Attribute.ARMOR)).getValue();
            p.sendActionBar(barAssemble());
        }
    }
}
