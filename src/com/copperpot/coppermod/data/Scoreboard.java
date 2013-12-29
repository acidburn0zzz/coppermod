package com.copperpot.coppermod.data;

import org.bukkit.entity.Player;

import java.util.HashMap;

public class Scoreboard extends HashMap<String, PlayerScore> {

    /**
     * Return a string of formatted PlayerScore entries
     * @return String
     */
    @Override
    public String toString() {
        String rtn = "";

        for (String key : this.keySet()) {
            rtn += this.get(key).toString();
        }

        return rtn;
    }

    /**
     * Retrieve a PlayerScore by Player
     * @param player
     * @return PlayerScore
     */
    public PlayerScore getByPlayer(Player player) {
        return this.get(player.getName());
    }
}
