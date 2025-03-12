package krek.tMCORE.weapons.predif;

import krek.tMCORE.Statistics.PlayerStats;
import krek.tMCORE.weapons.CustomWeapon;
import krek.tMCORE.weapons.abilities.WeaponAbility;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;

import java.util.List;

public class axe extends CustomWeapon {

    private boolean throwable;

    public axe(String name, List<Component> lore, Material itemVisual, WeaponAbility ability, PlayerStats reqStats, boolean throwable) {
        super(name, lore, itemVisual, ability, reqStats);
        this.throwable = throwable;
    }

    public boolean isThrowable() {
        return throwable;
    }

    public void setThrowable(boolean throwable) {
        this.throwable = throwable;
    }
}