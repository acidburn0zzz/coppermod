package com.copperpot.coppermod.listener;

import com.copperpot.coppermod.CopperMod;
import com.copperpot.coppermod.data.FartStreak;
import com.copperpot.coppermod.data.PlayerScore;
import com.copperpot.coppermod.event.FartOnEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public final class ScoreboardListener implements Listener {

    /**
     * Handle score updates for farts
     */
    @EventHandler
    public void onFart(FartOnEvent event) {
        PlayerScore scoreForPlayer = CopperMod.scoreboard.getByPlayer(event.getInstigator());
        PlayerScore scoreForVictim = CopperMod.scoreboard.getByPlayer(event.getVictim());

        if (event.getVictim().getUniqueId().equals(event.getInstigator().getUniqueId())) {
            return;
        }

        scoreForPlayer.incrementKills(1);
        scoreForPlayer.incrementStreakcounter(1);

        scoreForVictim.incrementDeaths(1);
        scoreForVictim.resetStreakCounter();

        if (scoreForPlayer.getStreakCounter() >= 3) {
            String streakMsg = FartStreak.getByAmount(scoreForPlayer.getStreakCounter()).label();
            if (streakMsg != null) {
                Bukkit.broadcastMessage(String.format(streakMsg, event.getInstigator().getName()));
            }
       }
    }


    /**
     * Create new PlayerScore entries for players as they join
     */
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        // dont overwrite existing scores
        if (CopperMod.scoreboard.containsKey(player.getName())) {
            return;
        }

        CopperMod.scoreboard.put(player.getName(), new PlayerScore(player));
    }
}
