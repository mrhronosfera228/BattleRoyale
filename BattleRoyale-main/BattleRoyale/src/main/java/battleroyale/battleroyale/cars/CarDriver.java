package battleroyale.battleroyale.cars;

import net.minecraft.server.v1_12_R1.*;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;

import java.lang.reflect.Field;

public class CarDriver extends EntitySheep {
    public CarDriver(Location loc) {
        super(((CraftWorld)loc.getWorld()).getHandle());
    }
    @Override
    public float g(float sideMot, float forMot) {
        if (this.passengers.size() > 0) {
            EntityLiving passenger = (EntityLiving) this.passengers.get(0);
            this.yaw = passenger.yaw;
            this.lastYaw = this.yaw;
            this.pitch = (passenger.pitch * 0.5F);
            setYawPitch(this.yaw, this.pitch);
            this.aN = this.yaw;
            this.aP = this.aN;
            float mnojitel = 3F;
            sideMot = passenger.be;
            forMot = passenger.bg;
            if (forMot <= 0.0F) {
                forMot *= 0.25F;
            }
            Field jump = null;
            try {
                jump = EntityLiving.class.getDeclaredField("bd");
            } catch (NoSuchFieldException e1) {
                e1.printStackTrace();
            } catch (SecurityException e1) {
                e1.printStackTrace();
            }
            jump.setAccessible(true);

            if (jump != null && this.onGround) {
                try {
                    if (jump.getBoolean(passenger)) {
                        double jumpHeight = 0.5D;
                        this.motY = jumpHeight;
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            super.a(sideMot, 0F, forMot);
        }
        return super.g(sideMot, forMot);
    }
}
