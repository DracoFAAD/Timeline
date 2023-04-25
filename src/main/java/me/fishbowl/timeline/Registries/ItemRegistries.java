package me.fishbowl.timeline.Registries;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemRegistries {
    public ItemStack moneyItemStack = new ItemStack(Material.DIAMOND);
    public void register() {
        ItemMeta meta = moneyItemStack.getItemMeta();
        meta.setLocalizedName("Money");
        meta.setDisplayName(ChatColor.RESET + "Money");
        meta.setCustomModelData(1);
        moneyItemStack.setItemMeta(meta);
    }
}
