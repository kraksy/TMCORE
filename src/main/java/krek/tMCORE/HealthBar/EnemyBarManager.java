package krek.tMCORE.HealthBar;

import net.kyori.adventure.text.Component;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Monster;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.Objects;

public class EnemyBarManager implements Listener {

    // vision █ █ █ █ █ | 3 ○

    String empty = "-";
    String solid = "█";
    String armor = String.valueOf('○');

    public String barAssemble(double maxHp, double hp, double av)
    {
        StringBuilder totalBar = new StringBuilder();
        double emptyHp = maxHp - hp;

        for (int i = 0; i < hp / 3; i++)
        {
            totalBar.append(solid);
        }
        for (int i = 0; i < emptyHp/ 3; i++)
        {
            totalBar.append(empty);
        }
        totalBar.append(" ".repeat(3));
        totalBar.append(av);
        totalBar.append(" ");
        totalBar.append(armor);

        return totalBar.toString();
    }

    @EventHandler
    public void update(EntityDamageEvent event) {
        Entity e = event.getEntity();
        if (e instanceof Monster m){
            double health = m.getHealth();
            double maxHealth = Objects.requireNonNull(m.getAttribute(Attribute.MAX_HEALTH)).getValue();
            double armor = Objects.requireNonNull(m.getAttribute(Attribute.ARMOR)).getValue();

            m.customName(Component.text(barAssemble(health, maxHealth, armor)));
        }
    }





















}
