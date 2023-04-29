package me.fishbowl.timeline.Libraries.BountyAPI;

import org.bukkit.entity.Player;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class BountyDataFile implements Serializable {
    public static class Tuple<T1, T2> {
        public T1 first;
        public T2 second;
        public Tuple(T1 t1, T2 t2) {
            first = t1;
            second = t2;
        }
    }

    public HashMap<UUID, Float> bountyList;


    public BountyDataFile(HashMap<UUID, Float> bountyList) {
        this.bountyList = bountyList;
    }

    public BountyDataFile(BountyDataFile dataFile) {
        this.bountyList = dataFile.bountyList;
    }

    public boolean saveData(String filePath) {
        try {
            BukkitObjectOutputStream out = new BukkitObjectOutputStream(new GZIPOutputStream(Files.newOutputStream(Paths.get(filePath))));
            out.writeObject(this);
            out.close();
            return true;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
    }

    public static BountyDataFile loadData(String filePath) {
        try {
            BukkitObjectInputStream in = new BukkitObjectInputStream(new GZIPInputStream(Files.newInputStream(Paths.get(filePath))));
            BountyDataFile data = (BountyDataFile) in.readObject();
            in.close();
            return data;
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
