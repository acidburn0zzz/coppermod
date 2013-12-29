package com.copperpot.coppermod.listener;

import com.copperpot.coppermod.CopperMod;
import com.copperpot.coppermod.data.PlayerScore;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

public final class ScoreboardListener extends BaseListener {
    public ScoreboardListener(CopperMod plugin) {
        super(plugin);
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
