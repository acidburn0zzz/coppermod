package com.copperpot.coppermod.command;

import com.copperpot.coppermod.CopperMod;
import com.copperpot.coppermod.data.PlayerScore;
import com.copperpot.coppermod.utils.Strings;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
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

        if (args.length > 0) {
            Player target = Bukkit.getServer().getPlayer(args[0]);

            if (target != null) {
                PlayerScore playerScore = plugin.getScoreForPlayer(target);
                msg = String.format(Strings.SCORE_PLAYER,
                        target.getName(),
                        playerScore.getKills(),
                        playerScore.getDeaths(),
                        playerScore.getRatio());
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
