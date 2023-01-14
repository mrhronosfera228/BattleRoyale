package battleroyale.battleroyale.events;

import battleroyale.battleroyale.BattleRoyale;
import battleroyale.battleroyale.items.RoyalItem;
import battleroyale.battleroyale.items.RoyalItemManager;
import org.bukkit.*;
import org.bukkit.block.Chest;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scoreboard.Scoreboard;

public class onClickChest implements Listener {
    Scoreboard board = Bukkit.getScoreboardManager().getMainScoreboard();
    @EventHandler
    public void onOpenChest(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (event.getClickedBlock() != null) {
            if (event.getClickedBlock().getType().equals(Material.CHEST) && event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                Chest chest = (Chest) event.getClickedBlock().getState();
                Location location = event.getClickedBlock().getLocation();
                for (int i = 0; i < chest.getBlockInventory().getSize(); i++) {
                    if (chest.getBlockInventory().getItem(i) != null) {
                        Item item = player.getWorld().dropItem(location, chest.getBlockInventory().getItem(i));
                        item.setGlowing(true);
                        //отключение свечения через 15 сек (20 тик = 1 сек)
                        Bukkit.getScheduler().runTaskLater(BattleRoyale.getInstance(), () -> {
                            item.setGlowing(false);
                                }, 300);
                        //удаление предмета с земли через 20 сек
                        Bukkit.getScheduler().runTaskLater(BattleRoyale.getInstance(), item::remove, 400);
                        //добавление предмета в команду (чтобы поменять цвет свечения)
                        RoyalItem ri = RoyalItemManager.getItem(chest.getBlockInventory().getItem(i));
                        if (ri != null) {
                            switch (ri.getPrototype().getQuality()) {
                                case COMMON:
                                    board.getTeam("COMMON").addEntry(item.getUniqueId().toString());
                                    break;
                                case UNCOMMON:
                                    board.getTeam("UNCOMMON").addEntry(item.getUniqueId().toString());
                                    break;
                                case RARE:
                                    board.getTeam("RARE").addEntry(item.getUniqueId().toString());
                                    break;
                                case EPIC:
                                    board.getTeam("EPIC").addEntry(item.getUniqueId().toString());
                                    break;
                                case MIFIC:
                                    board.getTeam("MIFIC").addEntry(item.getUniqueId().toString());
                                    break;
                                case ARTIFACT:
                                    board.getTeam("ARTIFACT").addEntry(item.getUniqueId().toString());
                                    break;
                                case LEGENDARY:
                                    board.getTeam("LEGENDARY").addEntry(item.getUniqueId().toString());
                                    break;
                            }
                        }
                    }
                }
                chest.getBlockInventory().clear();
                event.getClickedBlock().setType(Material.AIR);
            }
        }
    }
}
