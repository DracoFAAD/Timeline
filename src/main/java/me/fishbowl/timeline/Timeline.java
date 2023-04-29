package me.fishbowl.timeline;

import me.fishbowl.timeline.Registries.*;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.Objects;

public final class Timeline extends JavaPlugin {

    private static Timeline instance;

    public static String getPluginFolder() {
        File dataFolder = instance.getDataFolder();
        String path = dataFolder.getAbsolutePath() + "/TimelineConfig";

        File bountyFile = new File(path + "/BountyData.txt");

        if (!bountyFile.getParentFile().exists()) {
            bountyFile.getParentFile().mkdirs();
        }

        if (!bountyFile.exists()) {
            try {
                bountyFile.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


        return path;
    }

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
        libRegistry.bountyAPI.save();
    }

    public static Timeline getInstance() {
        return instance;
    }
}
