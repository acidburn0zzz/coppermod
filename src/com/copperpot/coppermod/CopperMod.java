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
    private Scoreboard scoreboard;

    @Override
    public void onEnable() {
        scoreboard = new Scoreboard();
        Bukkit.getPluginManager().registerEvents(new ScoreboardListener(this), this);

        getCommand("farton").setExecutor(new FartOnCommand(this));
        getCommand("fartscore").setExecutor(new FartScoreCommand(this));
        getCommand("autolocate").setExecutor(new AutoLocateCommand(this));
    }

    public Scoreboard getScoreboard() {
        return scoreboard;
    }

    public void log(String message) {
        getLogger().log(Level.INFO, message);
    }
}