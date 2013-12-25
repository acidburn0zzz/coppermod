package com.copperpot.coppermod;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Biome;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AutoLocateExecutor implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        Player actor = (Player) commandSender;
        Player target = Bukkit.getServer().getPlayer(args[0]);
        Location targetLocation = target.getLocation();

        Biome biome = target.getWorld().getBiome(targetLocation.getBlockX(), targetLocation.getBlockZ());

        actor.sendMessage(String.format("%s located in %s ( X:%.2f, Y:%.2f, Z:%.2f )", target.getName(), targetLocation.getWorld().getName(), targetLocation.getX(), targetLocation.getY(), targetLocation.getZ()));
        actor.sendMessage(String.format("They are in a %s.", StringUtils.capitalize(biome.toString().replace("_", ""))));

        return true;
    }
}
