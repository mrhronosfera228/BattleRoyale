package battleroyale.battleroyale.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

import static battleroyale.battleroyale.GameLogic.Game.GameStart;

public class onHunger implements Listener {
    public onHunger(){

    }
    @EventHandler
    public void onHunger(FoodLevelChangeEvent event) {
        if (!GameStart) {
            event.setCancelled(true);
        }
    }
}
