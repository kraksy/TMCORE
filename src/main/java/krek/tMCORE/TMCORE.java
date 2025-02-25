package krek.tMCORE;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class TMCORE extends JavaPlugin {

    Logger log = getLogger();

    @Override
    public void onEnable() {
        log.info("=====");
        log.info("starting");
        log.info("=====");

    }

    @Override
    public void onDisable() {
        log.info("ending");
    }
}
