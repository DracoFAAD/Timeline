package me.fishbowl.timeline.Commands;

import me.fishbowl.timeline.Timeline;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class givemoney implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("You can't give the console money!");
            return false;
        }

        Player player = (Player) sender;

        if (!player.hasPermission("timeline.givemoney") && !player.hasPermission("timeline.*")) {
            sender.sendMessage(ChatColor.RED + "You do not have enough permission.");
            return false;
        }

        int amount = 1;

        if (args.length > 0) {

        }

        player.getInventory().addItem(Timeline.getInstance().iRegistry.moneyItemStack);
        return true;
    }
}
