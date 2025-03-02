package krek.tMCORE.HealthBar;

import net.kyori.adventure.text.Component;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Monster;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntitySpawnEvent;

import java.util.Objects;

public class EnemyBarManager implements Listener {

    // vision █ █ █ █ █ | 3 ○

    Bar EnemyBar = new Bar(
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
        totalBar.append(EnemyBar.leftCorner);
        totalBar.append(EnemyBar.HV);
        totalBar.append(EnemyBar.HIcon);
        totalBar.append(EnemyBar.rightCorner);
        totalBar.append(" ".repeat(3));
        totalBar.append(EnemyBar.leftCorner);
        totalBar.append(EnemyBar.AV);
        totalBar.append(EnemyBar.AIcon);
        totalBar.append(EnemyBar.rightCorner);
        return totalBar.toString();
    }

    @EventHandler
    public void onSpawnEnemy(EntitySpawnEvent event)
    {
        Entity e = event.getEntity();
        if (e instanceof Monster m)
        {
            EnemyBar.HV = m.getHealth();
            EnemyBar.AV = Objects.requireNonNull(m.getAttribute(Attribute.ARMOR)).getValue();
            m.customName(Component.text(barAssemble()));
        }
    }

    @EventHandler
    public void onEnemyDamage(EntityDamageEvent event) {
        Entity e = event.getEntity();
        if (e instanceof Monster m){
            EnemyBar.HV = m.getHealth();
            EnemyBar.AV = Objects.requireNonNull(m.getAttribute(Attribute.ARMOR)).getValue();
            m.customName(Component.text(barAssemble()));
        }
    }





















}
