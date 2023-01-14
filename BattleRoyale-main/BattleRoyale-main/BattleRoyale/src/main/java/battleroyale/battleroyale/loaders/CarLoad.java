package battleroyale.battleroyale.loaders;

import battleroyale.battleroyale.cars.CarDriver;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.entity.CreatureSpawnEvent;

public class CarLoad {

    public static void load() {
        CreateCar(new Location(Bukkit.getWorld("battle"), 32, 10, 78));
    }

    private static void CreateCar(Location location) {
        CarDriver car = new CarDriver(location);
        car.setPosition(location.getX(), location.getY(), location.getZ());
        car.world.addEntity(car, CreatureSpawnEvent.SpawnReason.CUSTOM);
    }
}

