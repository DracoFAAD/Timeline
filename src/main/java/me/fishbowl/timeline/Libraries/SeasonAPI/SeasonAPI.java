package me.fishbowl.timeline.Libraries.SeasonAPI;

import me.fishbowl.timeline.Timeline;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Objects;

public class SeasonAPI {
    int daysPerSeason = 100;
    SeasonRegistry lastSeason = calculateSeason();
    public int calculateDays() {
        int fullTime = (int) Objects.requireNonNull(Bukkit.getWorld("world")).getFullTime();
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
        new BukkitRunnable() {
            @Override
            public void run() {
                SeasonRegistry newSeason = calculateSeason();
                if (newSeason != lastSeason) {
                    lastSeason = newSeason;
                    String seasonName = newSeason.name();
                    seasonName = seasonName.substring(0, 1).toUpperCase() + seasonName.substring(1).toLowerCase();
                    Bukkit.broadcastMessage(ChatColor.GOLD + "" + ChatColor.ITALIC + seasonName + " has arrived.");
                }
            }
        }.runTaskTimer(Timeline.getInstance(), 10, 10);
    }

}
