package me.fishbowl.timeline.Registries;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Iterator;

public class ItemRegistries {
    public ItemStack moneyItemStack = new ItemStack(Material.CLOCK);
    public void registry() {
        ItemMeta meta = moneyItemStack.getItemMeta();
        meta.setLocalizedName("Money");
        meta.setDisplayName("Money");
        meta.setCustomModelData(1);
        moneyItemStack.setItemMeta(meta);
    }
}
