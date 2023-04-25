package me.fishbowl.timeline.Commands;

import me.fishbowl.timeline.Timeline;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

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
            try {
                amount = Integer.parseInt(args[0]);
            } catch (Exception e){
                amount = 1;
            }
        }

        ItemStack item = Timeline.getInstance().iRegistry.moneyItemStack.clone();
        item.setAmount(amount);
        player.getInventory().addItem(item);
        return true;
    }
}
