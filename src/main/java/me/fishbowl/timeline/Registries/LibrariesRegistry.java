package me.fishbowl.timeline.Registries;

import me.fishbowl.timeline.Libraries.SeasonAPI.SeasonAPI;

public class LibrariesRegistry {
    public SeasonAPI seasonAPI = new SeasonAPI();
    public void register() {
        seasonAPI.register();
    }
}
