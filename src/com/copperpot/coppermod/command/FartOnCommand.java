package com.copperpot.coppermod.command;

import com.copperpot.coppermod.effect.FartOnEffect;
import com.copperpot.coppermod.event.FartOnEvent;
import com.copperpot.coppermod.utils.Strings;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FartOnCommand implements CommandExecutor {
    /**
     * Delivers a flatulent cloud of justice to the specified targets
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        Player instigator = (Player) sender;
        List<Player> targets = new ArrayList<Player>();

        // No player specified so fart on everyone
        if (args.length > 0) {
            Player target = Bukkit.getServer().getPlayer(args[0]);
            if (target != null) {
                targets.add(target);
            }
        } else {
            targets = instigator.getWorld().getPlayers();
        }

        fartOn(targets, instigator);

        return true;
    }

    /**
     * Broadcast the fart messages, smite the victims, and call a FartOnEvent
     * @param victims The ones who smelled it
     * @param instigator The one who dealt it
     */
    private void fartOn(List<Player> victims, Player instigator) {
        String victimLabel = victims.get(0).getName();

        if (victims.size() > 1) {
            victimLabel = "everyone";
        }

        broadcastFart(String.format(Strings.FART_ONE, instigator.getName(), Strings.getFartTerm(), victimLabel));

        for (Player victim : victims) {
            smite(victim);
            Bukkit.getServer().getPluginManager().callEvent(new FartOnEvent(instigator, victim));
        }
    }

    /**
     * Smite a Player with a fart
     */
    private void smite(Player victim) {
        float pitch = 0 + (2.0f - 0) * new Random().nextFloat();

        World world = victim.getWorld();
        Location location = victim.getLocation();

        world.strikeLightningEffect(location);
        world.playSound(location, Sound.GHAST_SCREAM2, 100, pitch);

        addFartOnEffect(victim);
    }

    /**
     * Let everyone know a fart happened.
     */
    private void broadcastFart(String msg) {
        Bukkit.broadcastMessage(ChatColor.GREEN + msg);
    }

    /**
     * Add a farted on status effect.
     */
    private void addFartOnEffect(Player player) {
        player.addPotionEffect(new FartOnEffect(PotionEffectType.CONFUSION, 100, 0, true), true);
    }
}
