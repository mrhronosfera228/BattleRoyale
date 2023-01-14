package battleroyale.battleroyale.events;

import battleroyale.battleroyale.player.RoyalPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityRegainHealthEvent;

//Реализация кастомной регенерации
public class onRegeneration implements Listener {
    public onRegeneration() {
    }
    @EventHandler
    public void onRegeneration(EntityRegainHealthEvent e) {
        e.setCancelled(true);
        Entity ent = e.getEntity();
        if (ent instanceof Player) {
            RoyalPlayer rp = RoyalPlayer.getPlayer(ent.getName());
            if (rp.getHealth() + rp.getRegeneration() > rp.getMaxHealth()) {
                rp.setHealth(rp.getMaxHealth());
            } else {
                rp.setHealth(rp.getHealth() + rp.getRegeneration());
            }
        }
    }
}
