package me.fishbowl.timeline.Commands;

import me.fishbowl.timeline.Timeline;
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

        player.getInventory().addItem(Timeline.getInstance().iRegistry.moneyItemStack);
        return true;
    }
}
