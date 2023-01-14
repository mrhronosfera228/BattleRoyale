package battleroyale.battleroyale.events;

import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

public class onDeathPlayer  implements Listener {

    @EventHandler
    public void DeathPlayer(EntityDeathEvent event) {
        if(event.getEntity() != null) {
            if(event.getEntity() instanceof Player) {
                //Создание сундука при убийстве игрока
                event.getDrops().clear();
                event.getEntity().getLocation().getBlock().setType(Material.CHEST);
                Chest chest = (Chest) event.getEntity().getLocation().getBlock().getState();
                Player player = (Player) event.getEntity();
                //Добавить мод на сундуки (41 слот через моды)
                for (int i = 0; i < player.getInventory().getSize(); i++) {
                    AddItem(player.getInventory().getItem(i), chest);
                }
            }
        }
    }
    public void AddItem(ItemStack itemStack, Chest chest)
    {
        if(itemStack != null) {
            chest.getBlockInventory().addItem(itemStack);
        }
    }
}
