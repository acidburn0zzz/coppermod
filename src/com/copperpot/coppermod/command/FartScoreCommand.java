package com.copperpot.coppermod.command;

import com.copperpot.coppermod.CopperMod;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FartScoreCommand extends BaseCommand {
    public FartScoreCommand(CopperMod plugin) {
        super(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        String msg = plugin.getScoreboard().toString();
        Player player = (Player) sender;

        if (args[0] != null) {
            Player target = Bukkit.getServer().getPlayer(args[0]);
            msg = String.format("%s has %s farts!", target.getName(), plugin.getScoreForPlayer(target));

            player.sendMessage(msg);
        } else {
            player.getServer().broadcastMessage(msg);
        }

        return true;
    }
}
