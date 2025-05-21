package krek.tMCORE.HealthBar;

import krek.tMCORE.Utils.NumberUtils;
import net.kyori.adventure.text.Component;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Monster;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.EntitySpawnEvent;

import java.util.Objects;

public class EnemyBarManager implements Listener {

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
        return EnemyBar.leftCorner +
                EnemyBar.HV +
                EnemyBar.HIcon +
                EnemyBar.rightCorner +
                " ".repeat(3) +
                EnemyBar.leftCorner +
                EnemyBar.AV +
                EnemyBar.AIcon +
                EnemyBar.rightCorner;
    }

    @EventHandler
    public void onSpawnEnemy(EntitySpawnEvent event)
    {
        Entity e = event.getEntity();
        if (e instanceof Monster m)
        {
            EnemyBar.HV = NumberUtils.format(m.getHealth());
            EnemyBar.AV = Objects.requireNonNull(m.getAttribute(Attribute.ARMOR)).getValue();
            m.customName(Component.text(barAssemble()));
        }
    }

    @EventHandler
    public void onEnemyHeal(EntityRegainHealthEvent event)
    {
        Entity e = event.getEntity();
        if (e instanceof Monster m)
        {
            EnemyBar.HV = NumberUtils.format(m.getHealth());
            EnemyBar.AV = Objects.requireNonNull(m.getAttribute(Attribute.ARMOR)).getValue();
            m.customName(Component.text(barAssemble()));
        }
    }

    @EventHandler
    public void onEnemyDamage(EntityDamageEvent event) {
        Entity e = event.getEntity();
        if (e instanceof Monster m){
            EnemyBar.HV = NumberUtils.format(m.getHealth());
            EnemyBar.AV = Objects.requireNonNull(m.getAttribute(Attribute.ARMOR)).getValue();
            m.customName(Component.text(barAssemble()));
        }
    }
}
