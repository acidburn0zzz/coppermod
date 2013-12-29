package com.copperpot.coppermod.data;

import org.bukkit.ChatColor;

public enum FartStreak {

    KILLING_SPREE(3),
    DOMINATING(4),
    MEGA_KILL(5),
    UNSTOPPABLE(6),
    WICKED_SICK(7),
    MONSTER_KILL(8),
    GODLIKE(9),
    BEYOND_GODLIKE(10);

    private final int triggerAmount;

    private FartStreak(int triggerAmount) {
        this.triggerAmount = triggerAmount;
    }

    /**
     * Get a KillStreak by an amount
     * @param amount
     * @return the KillStreak object
     */
    public static FartStreak getByAmount(int amount) {
        if (amount >= 10) {
            return FartStreak.BEYOND_GODLIKE;
        }

        for (FartStreak fartStreak : FartStreak.values()) {
            if (fartStreak.triggerAmount == amount) {
                return fartStreak;
            }
        }

        return null;
    }

    /**
     * Map killstreak amounts to their respective messages
     * @return Killstreak message
     */
    public String label() {
        switch (this) {
            case KILLING_SPREE:
                return "%s is on a " + ChatColor.GREEN + "Farting Spree!";
            case DOMINATING:
                return "%s is " + ChatColor.DARK_PURPLE + "dominating!";
            case MEGA_KILL:
                return "%s is on a " + ChatColor.LIGHT_PURPLE + " mega fart " + ChatColor.WHITE + "streak!";
            case UNSTOPPABLE:
                return "%s is " + ChatColor.YELLOW + " unstoppable!";
            case WICKED_SICK:
                return "%s is " + ChatColor.GOLD + "wicked sick!";
            case MONSTER_KILL:
                return "%s is on a " + ChatColor.DARK_RED + "monster fart " + ChatColor.WHITE + "streak!";
            case GODLIKE:
                return "%s is " + ChatColor.RED + "GODLIKE!";
            case BEYOND_GODLIKE:
                return "%s is " + ChatColor.RED + " BEYOND GODLIKE!" + ChatColor.WHITE + " SOMEONE FART ON THEM!!";
        }

        return null;
    }
}