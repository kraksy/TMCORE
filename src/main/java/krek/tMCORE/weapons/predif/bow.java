package krek.tMCORE.weapons.predif;

import krek.tMCORE.Statistics.PlayerStats;
import krek.tMCORE.weapons.CustomWeapon;
import krek.tMCORE.weapons.abilities.WeaponAbility;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;

import java.util.List;

public class bow extends CustomWeapon {

    public Arrow arrow;

    public bow(String name, List<Component> lore, Material itemVisual, WeaponAbility ability, PlayerStats reqStats, Arrow arrow) {
        super(name, lore, itemVisual, ability, reqStats);
        this.arrow = arrow;
    }

    public Arrow getArrow() {
        return arrow;
    }

    public void setArrow(Arrow arrow) {
        this.arrow = arrow;
    }
}
