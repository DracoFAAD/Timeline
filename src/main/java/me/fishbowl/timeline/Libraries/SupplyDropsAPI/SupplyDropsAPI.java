package me.fishbowl.timeline.Libraries.SupplyDropsAPI;

import me.fishbowl.timeline.Timeline;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.FallingBlock;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import org.bukkit.block.BlockState;

import java.util.Random;


public class SupplyDropsAPI implements Listener {
    public void register() {
        Bukkit.getServer().getPluginManager().registerEvents(this, Timeline.getInstance());

        new BukkitRunnable(){
            @Override
            public void run() {
                spawnSupplyDrop(Bukkit.getWorld("world"));
            }
        }.runTaskTimer(Timeline.getInstance(), (20*2)*1, (20*60)*60);
    }

    public int randomNumber(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }

    public void spawnSupplyDrop(World world) {
        int x = randomNumber(-2000, 2000);
        int z = randomNumber(-2000, 2000);
        x=-21;
        z=49;

        Block block = world.getHighestBlockAt(x, z);
        Location chestLocation = block.getLocation().add(new Vector(0, 1, 0));

        block = world.getBlockAt(chestLocation);

        block.setType(Material.CHEST);
        BlockState blockState = block.getState();
        if (blockState instanceof Chest) {
            Chest chest = (Chest) blockState;

            ItemStack[] contents = chest.getBlockInventory().getContents();
            for (int i = 0; i < contents.length; i++) {
                ItemStack itemStack = contents[i];
                if (itemStack == null) {
                    itemStack = new ItemStack(Material.DIAMOND);
                }

                int random = randomNumber(1, 5);
                if (random == 1) {
                    itemStack.setType(Material.DIAMOND);
                } else if (random < 4) {
                    itemStack.setType(Material.IRON_BLOCK);
                    itemStack.setAmount(randomNumber(1, 2));
                } else {
                    itemStack.setType(Material.AIR);
                }
                chest.getBlockInventory().setItem(i, itemStack);
            }
        }

        Bukkit.broadcastMessage(String.format("%sSupply drop spawned at: %s, %s%d, %s", ChatColor.AQUA, block.getLocation().getX(), block.getLocation().getY(), 1, block.getLocation().getZ()));
        Block finalBlock = block;
        new BukkitRunnable(){

            @Override
            public void run() {
                if (world.getBlockAt(finalBlock.getLocation()).getType() == Material.CHEST) {
                    world.getBlockAt(finalBlock.getLocation()).setType(Material.AIR);
                }
            }
        }.runTaskLater(Timeline.getInstance(), (20*60)*30);
    }
}
