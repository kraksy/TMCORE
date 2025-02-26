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

    Bar healthBar = new Bar(20 , "█", "-", "[", "]");
    Bar ManaBar = new Bar(20 , "█", "-", "[", "]");

    public String barAssemble(double maxHp, double hp)
    {
        StringBuilder totalBar = new StringBuilder();


        return
    }

    public String connectBars() {
        StringBuilder totalBar = new StringBuilder();


        totalBar.append(healthBar);
        totalBar.append(ManaBar);

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

            // im sure that this is not okay :)
            player.sendActionBar(Component.text(barAssemble(barComponent(health, maxHealth), barComponent(maxMana, mana))));
        }
    }


}
