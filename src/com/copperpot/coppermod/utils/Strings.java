package com.copperpot.coppermod.utils;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public abstract class Strings {
    public static String FART_EVERYONE = "%s just %s on everyone!";
    public static String FART_ONE = "%s just %s on %s!";
    public static String SCORE_PLAYER = "%s has %s kills, %s deaths [%.2f] \n";
    public static String ERR_NO_PLAYER = "No such player.";
    public static List<String> FART_TERMS = Arrays.asList(
            "tooted", "farted", "ripped one", "cut the cheese", "passed gas", "broke wind"
    );

    public static String getFartTerm() {
        return FART_TERMS.get(new Random().nextInt(FART_TERMS.size() - 1));
    }
}
