package com.copperpot.coppermod;

import com.copperpot.coppermod.utils.Strings;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Random;

public class FartOnExecutor implements CommandExecutor {

    /**
     * Handle the /farton command
     * @param sender
     * @param command
     * @param s
     * @param args
     * @return boolean
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        Player instigator = (Player) sender;

        if (args.length == 0) {
            fartOn(instigator.getWorld().getPlayers(), instigator);
        } else {
            fartOn(Bukkit.getServer().getPlayer(args[0]), instigator);
        }

        return true;
    }

    /**
     * Fart on multiple players
     * @param players
     * @param instigator
     */
    private void fartOn(List<Player> players, Player instigator) {
        broadcastFart(String.format(Strings.FART_EVERYONE, instigator.getName(), Strings.getFartTerm()));
        for (Player victim : players) {
            smite(victim);
        }
    }

    /**
     * Fart on a single player
     * @param victim
     * @param instigator
     */
    private void fartOn(Player victim, Player instigator) {
        broadcastFart(String.format(Strings.FART_ONE, instigator.getName(), Strings.getFartTerm(), victim.getName()));
        smite(victim);
    }

    /**
     * Smite someone with a fart
     * @param victim
     */
    private void smite(Player victim) {
        float pitch = 0 + (2.0f - 0) * new Random().nextFloat();

        World world = victim.getWorld();
        Location location = victim.getLocation();

        world.strikeLightningEffect(location);
        world.playSound(location, Sound.GHAST_SCREAM2, 100, pitch);
    }

    /**
     * Let everyone know a fart happened.
     * @param msg
     */
    private void broadcastFart(String msg) {
        Bukkit.broadcastMessage(ChatColor.GREEN + msg);
    }
}
