package me.fishbowl.timeline.Registries;

import me.fishbowl.timeline.Commands.givemoney;
import me.fishbowl.timeline.Timeline;

public class CommandsRegistry {
    public givemoney giveMoney = new givemoney();
    public void register() {
        Timeline.getInstance().getCommand("givemoney").setExecutor(giveMoney);
    }
}
