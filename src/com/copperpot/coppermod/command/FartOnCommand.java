package com.copperpot.coppermod.command;

import com.copperpot.coppermod.CopperMod;
import com.copperpot.coppermod.effects.FartOnEffect;
import com.copperpot.coppermod.utils.Strings;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FartOnCommand extends BaseCommand {

    public FartOnCommand(CopperMod plugin) {
        super(plugin);
    }

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

        try {
            plugin.updateScoreForPlayer(instigator, 1);
        } catch (Exception e) {
            Logger.getLogger("Minecraft").log(Level.INFO, e.getMessage());
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

        addFartOnEffect(victim);
    }

    /**
     * Let everyone know a fart happened.
     * @param msg
     */
    private void broadcastFart(String msg) {
        Bukkit.broadcastMessage(ChatColor.GREEN + msg);
    }

    /**
     * Add a farted on status effect.
     * @param player
     */
    private void addFartOnEffect(Player player) {
        player.addPotionEffect(new FartOnEffect(PotionEffectType.CONFUSION, 100, 0, true), true);
    }
}
