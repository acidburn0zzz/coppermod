package com.copperpot.coppermod.command;

import com.copperpot.coppermod.CopperMod;
import com.copperpot.coppermod.effects.FartOnEffect;
import com.copperpot.coppermod.event.FartOnEvent;
import com.copperpot.coppermod.utils.Strings;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

import java.util.List;
import java.util.Random;

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
        List<Player> targets = instigator.getWorld().getPlayers();

        // No player specified so fart on everyone
        if (args.length > 0) {
            Player target = Bukkit.getServer().getPlayer(args[0]);
            if (target != null) {
                targets.add(target);
            }
        }

        fartOn(targets, instigator);

        return true;
    }

    /**
     * Fart on multiple players
     * @param victims
     * @param instigator
     */
    private void fartOn(List<Player> victims, Player instigator) {
        if (victims.size() > 1) {
            broadcastFart(String.format(Strings.FART_EVERYONE, instigator.getName(), Strings.getFartTerm()));
        } else {
            broadcastFart(String.format(Strings.FART_ONE, instigator.getName(), Strings.getFartTerm(), victims.get(0).getName()));
        }

        for (Player victim : victims) {
            smite(victim);
            Bukkit.getServer().getPluginManager().callEvent(new FartOnEvent(instigator, victim));
        }
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
