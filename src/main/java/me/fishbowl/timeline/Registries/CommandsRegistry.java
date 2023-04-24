package me.fishbowl.timeline.Registries;

import me.fishbowl.timeline.Commands.givemoney;
import me.fishbowl.timeline.Timeline;
import org.bukkit.Bukkit;

public class CommandsRegistry {
    public givemoney giveMoney = new givemoney();
    public void registry() {
        Timeline.getInstance().getCommand("givemoney").setExecutor(giveMoney);
    }
}
