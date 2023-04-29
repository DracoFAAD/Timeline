package me.fishbowl.timeline.Registries;

import me.fishbowl.timeline.Libraries.BountyAPI.BountyAPI;
import me.fishbowl.timeline.Libraries.SeasonAPI.SeasonAPI;
import me.fishbowl.timeline.Libraries.SupplyDropsAPI.SupplyDropsAPI;

public class LibrariesRegistry {
    public SeasonAPI seasonAPI = new SeasonAPI();
    public BountyAPI bountyAPI = new BountyAPI();
    public SupplyDropsAPI supplyDropsAPI = new SupplyDropsAPI();
    public void register() {
        seasonAPI.register();
        bountyAPI.register();
        supplyDropsAPI.register();
    }
}
