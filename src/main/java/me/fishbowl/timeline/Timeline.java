package me.fishbowl.timeline;

import me.fishbowl.timeline.Registries.*;
import org.bukkit.plugin.java.JavaPlugin;

public final class Timeline extends JavaPlugin {

    private static Timeline instance;

    public ListenerRegistry lRegistry = new ListenerRegistry();
    public CommandsRegistry cRegistry = new CommandsRegistry();
    public ItemRegistries iRegistry = new ItemRegistries();
    public LibrariesRegistry libRegistry = new LibrariesRegistry();

    @Override
    public void onEnable() {
        instance = this;

        lRegistry.register();
        cRegistry.register();
        iRegistry.register();
        libRegistry.register();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Timeline getInstance() {
        return instance;
    }
}
