package com.copperpot.coppermod;

import com.copperpot.coppermod.command.AutoLocateCommand;
import com.copperpot.coppermod.command.FartOnCommand;
import com.copperpot.coppermod.command.FartScoreCommand;
import com.copperpot.coppermod.data.Scoreboard;
import com.copperpot.coppermod.listener.ScoreboardListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public final class CopperMod extends JavaPlugin {
    /**
     * Global scoreboard for record keeping
     */
    public static Scoreboard scoreboard = new Scoreboard();

    /**
     * Register all listeners and command executors when plugin is enabled
     */
    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new ScoreboardListener(), this);

        getCommand("farton").setExecutor(new FartOnCommand());
        getCommand("fartscore").setExecutor(new FartScoreCommand());
        getCommand("autolocate").setExecutor(new AutoLocateCommand());
    }

    public void log(String message) {
        getLogger().log(Level.INFO, message);
    }
}