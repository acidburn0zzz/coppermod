package com.copperpot.coppermod.command;

import com.copperpot.coppermod.CopperMod;
import com.copperpot.coppermod.data.PlayerScore;
import com.copperpot.coppermod.utils.Strings;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FartScoreCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        String msg = CopperMod.scoreboard.toString();
        Player player = (Player) sender;

        if (args.length > 0) {
            Player target = Bukkit.getServer().getPlayer(args[0]);

            if (target != null) {
                PlayerScore playerScore = CopperMod.scoreboard.getByPlayer(target);
                msg = playerScore.toString();
            } else {
                msg = Strings.ERR_NO_PLAYER;
            }

            player.sendMessage(ChatColor.AQUA + msg);
        } else {
            player.getServer().broadcastMessage(ChatColor.AQUA + msg);
        }

        return true;
    }
}
