package com.copperpot.coppermod.data;

import com.copperpot.coppermod.CopperMod;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: jamisonlottering
 * Date: 12/29/13
 * Time: 12:26 AM
 * To change this template use File | Settings | File Templates.
 */
public class Scoreboard extends HashMap<String, PlayerScore> {
    private CopperMod plugin;
    private HashMap<String, PlayerScore> scoreboard;

    public Scoreboard (CopperMod plugin) {
        this.plugin = plugin;
        this.scoreboard = new HashMap<String, PlayerScore>();
    }

    @Override
    public String toString() {
        String rtn = "";

        for (String key : scoreboard.keySet()) {
            PlayerScore playerScore = scoreboard.get(key);
            rtn = rtn + String.format("%s ~> %i kills | %i deaths \n", key, playerScore.getKills(), playerScore.getDeaths());
        }

        return rtn;
    }
}
