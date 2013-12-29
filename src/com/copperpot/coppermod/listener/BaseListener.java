package com.copperpot.coppermod.listener;

import com.copperpot.coppermod.CopperMod;
import org.bukkit.event.Listener;

public class BaseListener implements Listener {
    protected CopperMod plugin;
    public BaseListener(CopperMod plugin) {
        this.plugin = plugin;
    }
}
