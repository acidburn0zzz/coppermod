package com.copperpot.coppermod.effects;

import org.bukkit.potion.PotionEffectType;

public class FartOnEffectType extends PotionEffectType {
    protected FartOnEffectType(int id) {
        super(id);
    }

    @Override
    public double getDurationModifier() {
        return 1500;
    }

    @Override
    public String getName() {
        return "Recently Farted On";
    }

    @Override
    public boolean isInstant() {
        return false;
    }
}

