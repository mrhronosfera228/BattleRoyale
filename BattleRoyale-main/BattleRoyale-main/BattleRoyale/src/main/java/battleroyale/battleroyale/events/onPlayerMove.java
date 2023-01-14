package battleroyale.battleroyale.events;

import battleroyale.battleroyale.packets.ProtocolProvider;
import battleroyale.battleroyale.utils.UtilColor;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

public class onPlayerMove implements Listener {
    public onPlayerMove () {

    }
    @EventHandler
    public void onPlayerMove (PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (player.getNearbyEntities(5, 2, 5) != null) {
            for (Entity entity : player.getNearbyEntities(5, 2, 5)) {
                if (entity instanceof Item) {
                    try {
                        ProtocolProvider.sendMetadataPacket(player, entity, (byte)3, false);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
            for (Entity entity : player.getNearbyEntities(3, 2, 3)) {
                if (entity instanceof Item) {
                    if (getLookingAt(player, entity)) {
                        if (((Item) entity).getItemStack().getItemMeta().getDisplayName() != null) {
                            entity.setCustomName(UtilColor.toColor(((Item) entity).getItemStack().getItemMeta().getDisplayName()));
                            try {
                                ProtocolProvider.sendMetadataPacket(player, entity, (byte)3, true);
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
    }
    private boolean getLookingAt(Player player, Entity entity) {
        Location eye = player.getEyeLocation();
        Vector toEntity = entity.getLocation().toVector().subtract(eye.toVector());
        double dot = toEntity.normalize().dot(eye.getDirection());
        return dot > 0.99D;
    }
}
