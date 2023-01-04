package battleroyale.battleroyale.events;

import battleroyale.battleroyale.GameLogic.Game;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.meta.ItemMeta;

public class onDropTake implements Listener {
    @EventHandler
    public void onTakeDrop(PlayerInteractAtEntityEvent event) {
        Player player = event.getPlayer();
        if (event.getRightClicked() instanceof ArmorStand) {
            if (RoyalPlayerDeathEvent.armorStandInventoryMap.containsKey((ArmorStand) event.getRightClicked())) {
                event.setCancelled(true);
                player.openInventory(RoyalPlayerDeathEvent.armorStandInventoryMap.get((ArmorStand) event.getRightClicked()));
            }
        }
    }
    @EventHandler
    public void onMoneyClick(InventoryClickEvent event) {
        if (event.getInventory().contains(Material.GOLD_INGOT)) {
            if (event.getCurrentItem().getType().equals(Material.GOLD_INGOT)) {
                ItemMeta meta = event.getCurrentItem().getItemMeta();
                if (ChatColor.stripColor(meta.getDisplayName()).equalsIgnoreCase("Деньги")) {
                    Player player = (Player) event.getWhoClicked();
                    event.setCancelled(true);
                    event.getInventory().remove(event.getCurrentItem());
                    int PlayerBalance = Game.PlayerBalance.get(player.getName());
                    Game.PlayerBalance.put(player.getName(), PlayerBalance + Integer.parseInt(ChatColor.stripColor(String.valueOf(meta.getLore()).replaceAll("[\\[\\]]", ""))));
                }
            }
        }
    }
}
