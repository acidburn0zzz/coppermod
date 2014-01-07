package com.copperpot.coppermod.data;

import org.bukkit.entity.Player;

import java.util.HashMap;

public class Scoreboard extends HashMap<String, PlayerScore> {

    /**
     * Loop through the scoreboard values and concatenate all the player score strings
     *
     * @return 'Player x has y kills and z deaths ...'
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
     * Get the PlayerScore object for a target Player
     *
     * @param player The target player
     * @return The PlayerScore object for target player
     */
    public PlayerScore getByPlayer(Player player) {
        return this.get(player.getName());
    }
}
