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
        int earnedScore = 1;

        // No player specified so fart on everyone
        if (args.length == 0) {
            List<Player> targets = instigator.getWorld().getPlayers();
            fartOn(targets, instigator);
            earnedScore = targets.size() - 1; // dont count yourself
        } else {
            Player target = Bukkit.getServer().getPlayer(args[0]);

            if (target != null) {
                fartOn(target, instigator);

                // dont allow suicides to increase kill count
                if (instigator.getUniqueId().equals(target.getUniqueId())) {
                    earnedScore = 0;
                }
            }
        }

        try {
            plugin.getScoreboard().getByPlayer(instigator).incrementKills(earnedScore);
        } catch (Exception e) {
            Logger.getLogger("Minecraft").log(Level.INFO, e.toString());
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
            smite(victim, instigator.getUniqueId().equals(victim.getUniqueId()));
        }
    }

    /**
     * Fart on a single player
     * @param victim
     * @param instigator
     */
    private void fartOn(Player victim, Player instigator) {
        broadcastFart(String.format(Strings.FART_ONE, instigator.getName(), Strings.getFartTerm(), victim.getName()));
        smite(victim, instigator.getUniqueId().equals(victim.getUniqueId()));
    }

    /**
     * Smite someone with a fart
     * @param victim
     */
    private void smite(Player victim, boolean isSelf) {
        float pitch = 0 + (2.0f - 0) * new Random().nextFloat();

        World world = victim.getWorld();
        Location location = victim.getLocation();

        world.strikeLightningEffect(location);
        world.playSound(location, Sound.GHAST_SCREAM2, 100, pitch);

        addFartOnEffect(victim);

        try {
            plugin.getScoreboard().getByPlayer(victim).incrementDeaths((isSelf ? 0 : 1));
        } catch (Exception e) {
            Logger.getLogger("Minecraft").log(Level.INFO, e.getMessage());
        }
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
