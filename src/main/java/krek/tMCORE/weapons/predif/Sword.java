package krek.tMCORE.weapons.predif;

import krek.tMCORE.Statistics.PlayerStats;
import krek.tMCORE.weapons.CustomWeapon;
import krek.tMCORE.weapons.abilities.WeaponAbility;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;

import java.util.List;

public class Sword extends CustomWeapon {

  boolean isDualWield;

  public Sword(String name, List<Component> lore, Material itemVisual, WeaponAbility ability, PlayerStats reqStats, boolean isDualWield) {
    super(name, lore, itemVisual, ability, reqStats);
    this.isDualWield =  isDualWield;
  }

  public boolean isDualWield() {
    return isDualWield;
  }

  public void setDualWield(boolean dualWield) {
    isDualWield = dualWield;
  }
}
