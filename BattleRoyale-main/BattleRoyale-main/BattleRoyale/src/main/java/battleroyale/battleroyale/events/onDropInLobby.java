package battleroyale.battleroyale.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

import static battleroyale.battleroyale.GameLogic.Game.GameStart;

public class onDropInLobby implements Listener {
    public onDropInLobby() {

    }
    @EventHandler
    public void OnDropInLobby(PlayerDropItemEvent event) {
        if (!GameStart) {
            event.setCancelled(true);
        }
    }
}
