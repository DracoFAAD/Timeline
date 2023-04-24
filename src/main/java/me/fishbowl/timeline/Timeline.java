package me.fishbowl.timeline;

import me.fishbowl.timeline.Registries.CommandsRegistry;
import me.fishbowl.timeline.Registries.ListenerRegistry;
import org.bukkit.plugin.java.JavaPlugin;

public final class Timeline extends JavaPlugin {

    ListenerRegistry lRegistry = new ListenerRegistry();
    CommandsRegistry cRegistry = new CommandsRegistry();

    @Override
    public void onEnable() {
        lRegistry.register();
        cRegistry.registry();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
