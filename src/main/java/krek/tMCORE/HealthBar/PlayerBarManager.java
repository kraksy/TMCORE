package krek.tMCORE.HealthBar;

import krek.tMCORE.TMCORE;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
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

    // on heal update also

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        playerBar.HV = Objects.requireNonNull(player.getAttribute(Attribute.MAX_HEALTH)).getValue();
        playerBar.AV = Objects.requireNonNull(player.getAttribute(Attribute.ARMOR)).getValue();

        new BukkitRunnable() {
            @Override
            public void run() {
                player.sendActionBar(Component.text(barAssemble()));
            }
        }.runTaskTimer(TMCORE.getPlugin(TMCORE.class), 0L, 5L);
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof Player player) {
            double currentHealth = player.getHealth();
            playerBar.HV = currentHealth - event.getFinalDamage();
            player.sendActionBar(Component.text(barAssemble()));
        }
    }
}