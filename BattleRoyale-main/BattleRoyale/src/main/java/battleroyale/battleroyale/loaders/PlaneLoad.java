package battleroyale.battleroyale.loaders;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Arrow;
import org.bukkit.util.Vector;


public class PlaneLoad {
    private static Arrow planeStart;
    //Реализация стартового самолёта
    public static void load(){
        Location locStart = new Location(Bukkit.getWorld("battle"), 700, 100, 700);
        planeStart = Bukkit.getServer().getWorld("battle").spawnArrow(locStart, new Vector(690, 100, 690), 0,100);
        planeStart.setGravity(false);
    }

    public static Arrow getPlaneStart() {
        return planeStart;
    }
}

