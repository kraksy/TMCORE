package krek.tMCORE.HealthBar;

import net.kyori.adventure.text.Component;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Objects;


public class PlayerBarManager implements Listener {

    String solid = "█";
    String empty = "-";
    String leftCorner = "[";
    String rightCorner = "]";

    public String barAssemble(double maxHp, double hp)
    {
        StringBuilder totalBar = new StringBuilder();
        double emptyHp = maxHp - hp;

        totalBar.append(leftCorner);
        for (int i = 0; i < hp; i++)
        {
            totalBar.append(solid);
        }
        for (int i = 0; i < emptyHp; i++)
        {
            totalBar.append(empty);
        }
        totalBar.append(rightCorner);

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

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        String playerName = player.getName();
        double playerHealth = player.getHealth();
    }

    @EventHandler
    public void update(EntityDamageEvent event)
    {
        Entity entity = event.getEntity();

        if (entity instanceof Player) {
            Player player = ((Player) entity).getPlayer();
            assert player != null;
            double health = player.getHealth();
            double maxHealth = Objects.requireNonNull(player.getAttribute(Attribute.MAX_HEALTH)).getValue();
            double mana = 20;
            double maxMana = 20;

            player.sendActionBar(Component.text(connectBars(barAssemble(health, maxHealth), barAssemble(maxMana, mana))));
        }
    }


}
