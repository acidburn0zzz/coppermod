package com.copperpot.coppermod.data;

import org.bukkit.entity.Player;

public class PlayerScore {
    private Player player;
    private Integer kills = 0;
    private Integer deaths = 0;

    public PlayerScore (Player player) {
        this.player = player;
    }

    public Integer getKills() {
        return kills;
    }

    public void setKills(Integer kills) {
        this.kills = kills;
    }

    public Integer getDeaths() {
        return deaths;
    }

    public void setDeaths(Integer deaths) {
        this.deaths = deaths;
    }

    public Player getPlayer() {
        return player;
    }

    public void addKills(int amount) {
        this.kills += amount;
    }

    public void addDeaths(int amount) {
        this.deaths += amount;
    }

    public double getRatio() {
        return  this.kills / this.deaths;
    }
}
