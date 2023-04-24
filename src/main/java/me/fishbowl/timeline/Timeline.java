package me.fishbowl.timeline;

import me.fishbowl.timeline.Registries.CommandsRegistry;
import me.fishbowl.timeline.Registries.ItemRegistries;
import me.fishbowl.timeline.Registries.ListenerRegistry;
import org.bukkit.plugin.java.JavaPlugin;

public final class Timeline extends JavaPlugin {

    private static Timeline instance;

    public ListenerRegistry lRegistry = new ListenerRegistry();
    public CommandsRegistry cRegistry = new CommandsRegistry();
    public ItemRegistries iRegistry = new ItemRegistries();

    @Override
    public void onEnable() {
        instance = this;

        lRegistry.register();
        cRegistry.registry();
        iRegistry.registry();


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Timeline getInstance() {
        return instance;
    }
}
