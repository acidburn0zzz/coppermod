package com.copperpot.coppermod.data;

import com.copperpot.coppermod.utils.Strings;
import org.bukkit.entity.Player;

public class PlayerScore {
    private Player player;
    private Integer kills = 0;
    private Integer deaths = 0;

    public PlayerScore (Player player) {
        this.player = player;
    }

    @Override
    public String toString() {
        return String.format(Strings.SCORE_PLAYER,
                player.getName(),
                getKills(),
                getDeaths(),
                getRatio()
        );
    }

    public Integer getKills() {
        return kills;
    }

    public Integer getDeaths() {
        return deaths;
    }

    public void incrementKills(int amount) {
        this.kills += amount;
    }

    public void incrementDeaths(int amount) {
        this.deaths += amount;
    }

    public double getRatio() {
        if (this.deaths < 1) {
            return this.kills;
        }

        return  this.kills / this.deaths;
    }
}
