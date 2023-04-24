package me.fishbowl.timeline.Registries;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Iterator;

public class ItemRegistries {
    ItemStack moneyItemStack = new ItemStack(Material.DIAMOND);
    public void removeRecipes(ItemStack stack) {
        Iterator<Recipe> recipes = Bukkit.recipeIterator();
        while (recipes.hasNext()) {
            Recipe recipe = recipes.next();
            for (Recipe newRecipe : Bukkit.getRecipesFor(stack)) {
                if (recipe == newRecipe) {
                    recipes.remove();
                }
            }
        }
    }
    public void register() {
        ItemMeta meta = moneyItemStack.getItemMeta();
        meta.setLocalizedName("Money");
        meta.setDisplayName("Money");
        meta.setCustomModelData(1);
        moneyItemStack.setItemMeta(meta);
        removeRecipes(moneyItemStack);
    }
}
