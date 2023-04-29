package me.fishbowl.timeline.Listeners;

import me.fishbowl.timeline.Timeline;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.WanderingTrader;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MerchantRecipe;

import java.util.ArrayList;
import java.util.List;

public class CustomTraderHandler implements Listener {

    public MerchantRecipe createRecipe(ItemStack result, ItemStack demand) {
        MerchantRecipe recipe = new MerchantRecipe(result, 999999999);
        recipe.addIngredient(demand);
        return recipe;
    }

    @EventHandler
    public void onInteractBlock(PlayerInteractEvent event) {
        if (event.getItem() == null) return;
        if (!event.getItem().isSimilar(Timeline.getInstance().iRegistry.wanderingTraderSpawner)) return;
        if (event.getClickedBlock() == null) return;
        if (event.getClickedBlock().getType() != Material.SOUL_CAMPFIRE) return;
        if (event.getHand() != EquipmentSlot.HAND) return;

        event.getItem().setAmount(event.getItem().getAmount() - 1);

        World world = event.getPlayer().getWorld();
        Block block = event.getClickedBlock();
        Entity entity = world.spawnEntity(block.getLocation(), EntityType.WANDERING_TRADER);
        WanderingTrader wanderingTrader = (WanderingTrader) entity;
        List<MerchantRecipe> recipeList = new ArrayList<>();
        recipeList.add(createRecipe(new ItemStack(Material.DIAMOND), new ItemStack(Material.COD)));
        wanderingTrader.setRecipes(recipeList);
    }
}
