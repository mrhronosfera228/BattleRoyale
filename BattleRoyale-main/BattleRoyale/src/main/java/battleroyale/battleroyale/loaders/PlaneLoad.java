package battleroyale.battleroyale.loaders;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Snowball;


public class PlaneLoad {
    public static Entity planeStart;
    //Реализация стартового самолёта
    public static void load(){
        Location locStart = new Location(Bukkit.getWorld("world"), 709, 100, 709);
        planeStart = Bukkit.getServer().getWorld("world").spawn(locStart, Snowball.class);
        planeStart.setGravity(false);
    }
}

