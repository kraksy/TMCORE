package krek.tMCORE.HealthBar;

import krek.tMCORE.TMCORE;
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

    public String barAssemble()
    {
        StringBuilder totalBar = new StringBuilder();
        totalBar.append(playerBar.leftCorner);
        totalBar.append(playerBar.HV);
        totalBar.append(playerBar.HIcon);
        totalBar.append(playerBar.rightCorner);
        for (int i = 0; i < 3; i++)
        {
            totalBar.append(" ");
        }
        totalBar.append(playerBar.leftCorner);
        totalBar.append(playerBar.MV);
        totalBar.append(playerBar.MIcon);
        totalBar.append(playerBar.rightCorner);
        for (int i = 0; i < 3; i++)
        {
            totalBar.append(" ");
        }
        totalBar.append(playerBar.leftCorner);
        totalBar.append(playerBar.AV);
        totalBar.append(playerBar.AIcon);
        totalBar.append(playerBar.rightCorner);
        return  totalBar.toString();
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
                player.sendActionBar(Component.text(barAssemble()));
            }
        }.runTaskTimer(TMCORE.getPlugin(TMCORE.class), 0L, 5L);

    }

    // below is prob not important
    @EventHandler
    public void onPlayerHeal(EntityRegainHealthEvent event)
    {
        if (event.getEntity() instanceof Player p)
        {
            playerBar.HV = p.getHealth();
            playerBar.AV = Objects.requireNonNull(p.getAttribute(Attribute.ARMOR)).getValue();
            p.sendActionBar(Component.text(barAssemble()));
        }
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageByEntityEvent event)
    {
        if (event.getEntity() instanceof Player p) {
            playerBar.HV = p.getHealth();
            playerBar.AV = Objects.requireNonNull(p.getAttribute(Attribute.ARMOR)).getValue();
            p.sendActionBar(Component.text(barAssemble()));
        }
    }
}