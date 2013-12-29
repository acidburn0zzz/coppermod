package com.copperpot.coppermod.data;

import com.copperpot.coppermod.CopperMod;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: jamisonlottering
 * Date: 12/29/13
 * Time: 12:26 AM
 * To change this template use File | Settings | File Templates.
 */
public class Scoreboard extends HashMap<String, Integer> {
    private CopperMod plugin;
    private HashMap<String, Integer> scoreboard;

    public Scoreboard (CopperMod plugin) {
        this.plugin = plugin;
        this.scoreboard = new HashMap<String, Integer>();
    }

    @Override
    public String toString() {
        String rtn = "";

        for (Map.Entry<String, Integer> entry : scoreboard.entrySet()) {
            rtn = rtn + String.format("%s ~> %s farts! \n", entry.getKey(), entry.getValue());
        }

        return rtn;
    }
}
