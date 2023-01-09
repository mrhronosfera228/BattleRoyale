package battleroyale.battleroyale.GameLogic;

import battleroyale.battleroyale.BattleRoyale;
import battleroyale.battleroyale.utils.UtilColor;
import net.minecraft.server.v1_12_R1.EntityArmorStand;
import net.minecraft.server.v1_12_R1.World;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitTask;

import java.util.Random;

public class DamageIndicator {
    private static Random r = new Random();
    private static ArmorStand createIndicator(Location loc, boolean offset, int seconds, String text) {
        final World w = ((CraftWorld)loc.getWorld()).getHandle();
        final EntityArmorStand eas = new EntityArmorStand(w);
        text = UtilColor.toColor(text);
        if (offset) {
            int i = r.nextInt(4);
            loc = loc.add(i == 0 ? 0.5 : i == 1 ? -0.5 : i == 2 ? 1 : -1, 0.0, 0.0);
            loc.add(0, 2, 0);
            i = r.nextInt(4);
            loc = loc.add(0.0, 0.0, i == 0 ? 0.5 : i == 1 ? -0.5 : i == 2 ? 1 : -1);
        }
        double x = loc.getX();
        double y = loc.getY();
        double z = loc.getZ();
        eas.setInvisible(true);
        eas.setSmall(true);
        eas.setArms(false);
        eas.setBasePlate(false);
        eas.noclip = true;
        eas.setCustomNameVisible(true);
        eas.setCustomName(text);
        eas.setPosition(x, y, z);
        w.addEntity(eas, CreatureSpawnEvent.SpawnReason.CUSTOM);
        Entity ent = eas.getBukkitEntity();
        ArmorStand as = (ArmorStand)ent;
        as.setMarker(true);
        ent.setMetadata("royal_di", new FixedMetadataValue(BattleRoyale.getInstance(), true));
        as.setVelocity(as.getVelocity().setY(0.4));
        BukkitTask task = Bukkit.getServer().getScheduler().runTaskTimer(BattleRoyale.getInstance(), () -> {
            as.setVelocity(as.getVelocity().setY(-0.3));
        }, 6, 2);
        Bukkit.getServer().getScheduler().runTaskLater(BattleRoyale.getInstance(), () -> {
            task.cancel();
            w.removeEntity(eas);
        }, 20L * (long)seconds);
        return as;
    }

    public static ArmorStand createIndicator(Location loc, boolean offset, int seconds, String text, Object... args) {
        return createIndicator(loc, offset, seconds, String.format(text, args));
    }
}
