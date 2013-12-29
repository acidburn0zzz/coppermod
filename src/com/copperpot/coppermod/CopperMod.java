package com.copperpot.coppermod;

import com.copperpot.coppermod.command.AutoLocateCommand;
import com.copperpot.coppermod.command.FartOnCommand;
import com.copperpot.coppermod.command.FartScoreCommand;
import com.copperpot.coppermod.data.PlayerScore;
import com.copperpot.coppermod.data.Scoreboard;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class CopperMod extends JavaPlugin {

    private Scoreboard scoreboard;
    public Logger log;

    public CopperMod() {
        log = Logger.getLogger("Minecraft");
    }

    @Override
    public void onEnable() {
        scoreboard = new Scoreboard(this);
        getCommand("farton").setExecutor(new FartOnCommand(this));
        getCommand("fartscore").setExecutor(new FartScoreCommand(this));
        getCommand("autolocate").setExecutor(new AutoLocateCommand(this));
    }

    public Scoreboard getScoreboard() {
        return scoreboard;
    }

    public PlayerScore getScoreForPlayer(Player instigator) {
        return scoreboard.getByPlayer(instigator);
    }

    public void updateScoreForPlayer(Player player, int amount) {
        PlayerScore currentScore = scoreboard.getByPlayer(player);
        currentScore.addKills(amount);

        scoreboard.put(player.getName(), currentScore);
    }

    public void updateDeathsForPlayer(Player victim, int amount) {
        PlayerScore currentScore = scoreboard.getByPlayer(victim);
        currentScore.addDeaths(amount);

        scoreboard.put(victim.getName(), currentScore);
    }
}