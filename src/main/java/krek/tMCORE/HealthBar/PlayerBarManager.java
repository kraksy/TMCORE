package krek.tMCORE.HealthBar;

import krek.tMCORE.TMCORE;
import net.kyori.adventure.text.Component;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

import static jdk.javadoc.internal.doclets.toolkit.util.DocPath.empty;


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

    public Bar barAssemble()
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
        return  totalBar.toString();
    }

    public String connectBars(String hpBar, String manaBar) {
        StringBuilder totalBar = new StringBuilder();

        totalBar.append(hpBar);
        for (int i = 0; i < 3; i++)
        {
            totalBar.append(" ");
        }
        totalBar.append(manaBar);

        return totalBar.toString();
    }

    // on join give bars , on damage update

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        new BukkitRunnable() {
            @Override
            public void run() {
                double playerHealth = player.getHealth();
                double playerMaxHealth = Objects.requireNonNull(player.getAttribute(Attribute.MAX_HEALTH)).getValue();
                double mana = 20;
                double maxMana = 20;

                player.sendActionBar(Component.text(connectBars(barAssemble(playerHealth, playerMaxHealth), barAssemble(maxMana, mana))));
            }
        }.runTaskTimer(TMCORE.getPlugin(TMCORE.class), 0L, 5L);
    }
}