package com.copperpot.coppermod.listener;

import com.copperpot.coppermod.CopperMod;
import com.copperpot.coppermod.data.FartStreak;
import com.copperpot.coppermod.data.PlayerScore;
import com.copperpot.coppermod.event.FartOnEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.logging.Level;

public final class ScoreboardListener extends BaseListener {
    public ScoreboardListener(CopperMod plugin) {
        super(plugin);
    }

    /**
     * Handle score updates for farts
     * @param event
     */
    @EventHandler
    public void onFart(FartOnEvent event) {
        PlayerScore scoreForPlayer = plugin.getScoreboard().getByPlayer(event.getInstigator());
        PlayerScore scoreForVictim = plugin.getScoreboard().getByPlayer(event.getVictim());

        if (event.getVictim().getUniqueId().equals(event.getInstigator().getUniqueId())) {
            return;
        }

        scoreForPlayer.incrementKills(1);
        scoreForPlayer.incrementStreakcounter(1);

        scoreForVictim.incrementDeaths(1);
        scoreForVictim.resetStreakCounter();

        if (scoreForPlayer.getStreakCounter() >= 3) {
            try {
                String streakMsg = FartStreak.getByAmount(scoreForPlayer.getStreakCounter()).label();
                if (streakMsg != null) {
                    Bukkit.broadcastMessage(String.format(streakMsg, event.getInstigator().getName()));
                }
            } catch (Exception e) {
                plugin.getLogger().log(Level.INFO, "Couldnt broadcast streak message");
            }
        }
    }

    /**
     * Create new PlayerScore entries for players as they join
     * @param event
     */
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        // dont overwrite existing scores
        if (plugin.getScoreboard().containsKey(player.getName())) {
            return;
        }

        plugin.log(String.format("Created scoreboard for %s", player.getName()));
        plugin.getScoreboard().put(player.getName(), new PlayerScore(player));
    }
}
