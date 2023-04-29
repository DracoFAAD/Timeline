package me.fishbowl.timeline.Libraries.SeasonAPI;

import me.fishbowl.timeline.Timeline;
import org.bukkit.*;
import org.bukkit.block.data.Ageable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockGrowEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.world.StructureGrowEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SeasonAPI implements Listener {
    int daysPerSeason = 100;
    SeasonRegistry lastSeason;
    public int calculateDays() {
        int fullTime = (int) Bukkit.getWorld("world").getFullTime();
        int days = (int) Math.floor((double) fullTime / 24000);
        return days;
    }

    public SeasonRegistry calculateSeason() {
        int day = calculateDays();
        int season = day - ((int) Math.floor((double) day / (daysPerSeason * 4)) * (daysPerSeason * 4));
        if (season == 0) season = 1;
        int NewSeason = (int) Math.floor(((double) season / daysPerSeason * 100) / 100 );

        if(NewSeason == 0) {
            return SeasonRegistry.Winter;
        }
        else if (NewSeason == 1) {
            return SeasonRegistry.Spring;
        }else if(NewSeason == 2){
            return SeasonRegistry.Summer;
        }else if(NewSeason == 3) {
            return SeasonRegistry.Fall;
        }

        return null;
    }

    public void register() {

        final boolean[] stopSnowing = {false};
        BukkitRunnable snowRunnable = new BukkitRunnable() {
            @Override
            public void run() {
                if (lastSeason == SeasonRegistry.Winter) {
                    if (stopSnowing[0] == false) {
                        World world = Bukkit.getWorld("world");
                        if (world != null) {
                            for (Player p : Bukkit.getOnlinePlayers()) {
                                world.spawnParticle(Particle.SNOWFLAKE, p.getLocation(), 250, 9, 12, 9, 0.00001);
                            }
                        /*
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute at @a run particle minecraft:end_rod ~ ~ ~ 9 12 9 0.01 10 normal");
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute at @a run particle minecraft:white_ash ~ ~ ~ 9 12 9 0.00001 2550 normal");
                        */
                        }
                    }
                }
            }
        };

        new BukkitRunnable() {
            @Override
            public void run() {
                stopSnowing[0] = !stopSnowing[0];
            }
        }.runTaskTimer(Timeline.getInstance(), (20*60)*20, (20*60)*20);

        new BukkitRunnable() {
            @Override
            public void run() {
                if (Bukkit.getWorld("world") == null) {
                    return;
                }

                if (Bukkit.getWorld("world") != null && lastSeason == null) {
                    lastSeason = calculateSeason();
                    snowRunnable.runTaskTimer(Timeline.getInstance(), 1, 1);
                }

                SeasonRegistry newSeason = calculateSeason();
                if (newSeason != lastSeason) {
                    lastSeason = newSeason;
                    String seasonName = newSeason.name();
                    seasonName = seasonName.substring(0, 1).toUpperCase() + seasonName.substring(1).toLowerCase();

                    String title = "";
                    String subTitle = "";

                    if (newSeason == SeasonRegistry.Summer) {
                        title = ChatColor.YELLOW + "New season!";
                        subTitle = ChatColor.YELLOW + "" + ChatColor.ITALIC + seasonName + " has arrived!";
                    }

                    if (newSeason == SeasonRegistry.Spring) {
                        title = ChatColor.GOLD + "New season!";
                        subTitle = ChatColor.GOLD + "" + ChatColor.ITALIC + seasonName + " has arrived!";
                    }

                    if (newSeason == SeasonRegistry.Fall) {
                        title = ChatColor.DARK_RED + "New season!";
                        subTitle = ChatColor.GOLD + "" + ChatColor.ITALIC + seasonName + " has arrived!";
                    }

                    if (newSeason == SeasonRegistry.Winter) {
                        title = ChatColor.WHITE + "New season!";
                        subTitle = ChatColor.AQUA + "" + ChatColor.ITALIC + seasonName + " has arrived!";
                    }

                    for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                        onlinePlayer.sendTitle(title, subTitle, 20, 40, 20);
                    }
                }
            }
        }.runTaskTimer(Timeline.getInstance(), 10, 10);


        Bukkit.getServer().getPluginManager().registerEvents(this, Timeline.getInstance());


    }

    @EventHandler
    public void PlayerJoined(PlayerJoinEvent event) {
        SeasonRegistry newSeason = calculateSeason();
        String seasonName = newSeason.name();
        seasonName = seasonName.substring(0, 1).toUpperCase() + seasonName.substring(1).toLowerCase();
        event.getPlayer().sendMessage(ChatColor.GOLD + "Welcome back! The current season is " + seasonName + "!");
    }

    @EventHandler
    public void OnCropGrow(BlockGrowEvent event) {
        if (lastSeason != SeasonRegistry.Winter) return;
        if (event.getBlock().getType() == Material.BEETROOTS) return;

        event.setCancelled(true);
    }

    @EventHandler
    public void OnTreeGrow(StructureGrowEvent event) {
        if (lastSeason == SeasonRegistry.Winter) {
            event.setCancelled(true);
        }
    }
}
