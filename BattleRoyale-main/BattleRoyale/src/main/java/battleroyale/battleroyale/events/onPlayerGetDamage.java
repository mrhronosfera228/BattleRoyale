package battleroyale.battleroyale.events;

import battleroyale.battleroyale.GameLogic.Game;
import battleroyale.battleroyale.player.RoyalPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class onPlayerGetDamage implements Listener {
    public onPlayerGetDamage() {
    }
    @EventHandler
    public void onPlayerGetDamage(EntityDamageEvent event) {
        event.setCancelled(true);
        if (Game.GameStart) {
            if (event.getEntity() instanceof Player) {
                Player player = (Player) event.getEntity();
                RoyalPlayer rp = RoyalPlayer.getPlayer(player.getName());
                if (event.getCause() == EntityDamageEvent.DamageCause.FALL || event.getCause() == EntityDamageEvent.DamageCause.FIRE_TICK) {
                    rp.damage(null, (int) event.getDamage());
                }
            }
        }
    }
}
