package me.fishbowl.timeline.Registries;

import me.fishbowl.timeline.Listeners.CustomTraderHandler;
import me.fishbowl.timeline.Timeline;
import org.bukkit.Bukkit;

public class ListenerRegistry {
    public CustomTraderHandler customTraderHandler = new CustomTraderHandler();
    public void register() {
        Bukkit.getServer().getPluginManager().registerEvents(customTraderHandler, Timeline.getInstance());
    }
}
