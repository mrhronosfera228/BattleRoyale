package battleroyale.battleroyale.events;

import battleroyale.battleroyale.GameLogic.Game;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.inventory.meta.ItemMeta;

public class onPickUpItem implements Listener {
    //Система поднятия денег с пола
    @EventHandler
    public void onPickupItem(EntityPickupItemEvent event) {
        if (event.getEntity() != null) {
            if (event.getEntity() instanceof Player) {
                Player player = (Player) event.getEntity();
                if (event.getItem().getItemStack().getType().equals(Material.GOLD_INGOT)) {
                    ItemMeta meta = event.getItem().getItemStack().getItemMeta();
                    if (ChatColor.stripColor(meta.getDisplayName()).equalsIgnoreCase("Деньги")) {
                        event.getItem().remove();
                        event.setCancelled(true);
                        int PlayerBalance = Game.PlayerBalance.get(player.getName());
                        Game.PlayerBalance.put(player.getName(), PlayerBalance + Integer.parseInt(ChatColor.stripColor(String.valueOf(meta.getLore()).replaceAll("[\\[\\]]", ""))));
                    }
                }
            }
        }
    }
}
