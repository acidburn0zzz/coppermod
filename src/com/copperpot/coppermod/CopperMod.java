package com.copperpot.coppermod;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class CopperMod extends JavaPlugin {

    public Logger log;

    public CopperMod() {
        log = Logger.getLogger("Minecraft");
    }

    @Override
    public void onEnable() {
        getCommand("farton").setExecutor(new FartOnExecutor());
        getCommand("autolocate").setExecutor(new AutoLocateExecutor());
    }
}