package me.fishbowl.timeline.Libraries.BountyAPI;

import me.fishbowl.timeline.Timeline;
import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.*;

public class BountyAPI implements Listener {
    public BountyDataFile bountyDataFile;
    public void register() {
        Bukkit.getServer().getPluginManager().registerEvents(this, Timeline.getInstance());
        bountyDataFile = BountyDataFile.loadData(Timeline.getPluginFolder() + "/BountyData.txt");
        if (bountyDataFile == null) {
            bountyDataFile = new BountyDataFile(new HashMap<>());
        }
    }

    public void save() {
        String path = Timeline.getPluginFolder() + "/BountyData.txt";
        boolean succeeded = bountyDataFile.saveData(Timeline.getPluginFolder() + "/BountyData.txt");
    }

    public boolean hasBounty(Player player) {
        for (Map.Entry<UUID, Float> entry : bountyDataFile.bountyList.entrySet()) {
            if (Objects.equals(player.getUniqueId().toString(), entry.getKey().toString())) return true;
        }
        return false;
    }

    public void addBounty(Player player, float amount) {
        for (Map.Entry<UUID, Float> entry : bountyDataFile.bountyList.entrySet()) {
            if (Objects.equals(player.getUniqueId().toString(), entry.getKey().toString())) {
                entry.setValue(entry.getValue() + amount);
                return;
            }
        }

        Bukkit.broadcastMessage("" + bountyDataFile.bountyList.size());
        bountyDataFile.bountyList.put(player.getUniqueId(), amount);
    }

    public void removeBounty(Player player) {
        bountyDataFile.bountyList.remove(player.getUniqueId());
    }

    public float getBounty(Player player) {
        if (bountyDataFile.bountyList.containsKey(player.getUniqueId())) {
            return bountyDataFile.bountyList.get(player.getUniqueId());
        }
        return 0f;
    }



    @EventHandler
    public void PlayerDiedEvent(PlayerDeathEvent event) {
        Player target = event.getEntity();
        Player killer = event.getEntity().getKiller();

        if (killer == null) return;
        //TODO: Add money to the killer if the target has bounty.
        if (hasBounty(target)) return;

        addBounty(killer, (float) Math.floor(getBounty(killer) * 0.05f) + 1000);

        Bukkit.broadcastMessage("Bounty increased! " + killer.getDisplayName() + "'s bounty has been increased to: " + getBounty(killer));
    }
}
