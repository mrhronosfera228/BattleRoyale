package battleroyale.battleroyale.cars;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;


public class SpawnArrow {
    public static Entity arrowStart;
    public static Arrow arrowFly;
    public static void load(){
        Location locStart = new Location(Bukkit.getWorld("world"), 709, 100, 709);
        Location locFly = new Location(Bukkit.getWorld("world"), 729, 120, 729);
        arrowStart = Bukkit.getServer().getWorld("world").spawnArrow(locStart, new Vector(708, 100, 708), 0F, 10F);
        arrowStart.setGravity(false);
        arrowFly = Bukkit.getServer().getWorld("world").spawnArrow(locFly, new Vector(0, 100, 0), 0.7F, 0);
        arrowFly.setGravity(false);
    }
}

