package com.copperpot.coppermod.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public final class FartOnEvent extends Event {
    private static final HandlerList handlers = new HandlerList();

    private Player victim;
    private Player instigator;

    public FartOnEvent(Player instigator, Player victim) {
        this.instigator = instigator;
        this.victim = victim;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public Player getVictim() {
        return victim;
    }

    public Player getInstigator() {
        return instigator;
    }
}

